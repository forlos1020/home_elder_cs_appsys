package com.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.constance.Operation;
import com.user.constance.ServiceState;
import com.user.mapper.ServiceOrderMapper;
import com.user.pojo.*;
import com.user.pojo.Dto.UserDTO;
import com.user.pojo.param.EvaluateParams;
import com.user.pojo.param.OrderListParams;
import com.user.pojo.param.OrderParam;
import com.user.service.*;
import com.user.utils.SystemUtils;
import com.user.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDateTime;

/**
 * @author ASUS
 * @description 针对表【service_order(服务订单)】的数据库操作Service实现
 * @createDate 2023-12-11 16:18:21
 */
@Service
@AllArgsConstructor
public class ServiceOrderServiceImpl extends ServiceImpl<ServiceOrderMapper, ServiceOrder>
        implements ServiceOrderService {

    private final IOrderOperationService orderOperationService;
    private final CareProviderInfoService careProviderInfoService;
    private final SystemUtils systemUtils;
    private GuardianInfoService guardianService;
    private ElderStateService elderService;
    private static final Logger logger = LoggerFactory.getLogger(ServiceOrderService.class);


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result takeOrder(OrderParam param) {
        UserDTO currentUser = null;
        try {
            currentUser = systemUtils.getCurrentUser(param.getUserPhone());
        } catch (UnsupportedEncodingException e) {
            logger.info("获取当前对象失败");
            throw new RuntimeException(e);
        }
        if (currentUser.getUserType() != 3) {
            return Result.error("用户类型不匹配");
        }
        this.update(new LambdaUpdateWrapper<ServiceOrder>()
                .eq(ServiceOrder::getOrderId, param.getOrderId())
                .set(ServiceOrder::getServProvId, currentUser.getUserId())
                .set(ServiceOrder::getServProvName, currentUser.getUserRealname())
                .set(ServiceOrder::getServState, ServiceState.RECEIVED));
        orderOperationService.save(new OrderOperation(param.getOrderId(), currentUser.getUserId(),
                currentUser.getUserRealname(), Operation.RECEIVE, LocalDateTime.now()));

        return Result.success("请求成功");
    }

    @Override
    public Result confirmOrder(OrderParam param) {
        UserDTO currentUser = null;
        try {
            currentUser = systemUtils.getCurrentUser(param.getUserPhone());
        } catch (UnsupportedEncodingException e) {
            logger.info("获取当前对象失败");
            throw new RuntimeException(e);
        }
        this.lambdaUpdate().eq(ServiceOrder::getOrderId, param.getOrderId())
                .set(ServiceOrder::getServState, ServiceState.CONFIRM).update();
        orderOperationService.save(new OrderOperation(param.getOrderId(), currentUser.getUserId(),
                currentUser.getUserRealname(), Operation.CONFIRM, LocalDateTime.now()));
        return Result.success("请求成功");
    }

    @Override
    public Result startOrder(OrderParam param) {
        UserDTO currentUser = null;
        try {
            currentUser = systemUtils.getCurrentUser(param.getUserPhone());
        } catch (UnsupportedEncodingException e) {
            logger.info("获取当前对象失败");
            throw new RuntimeException(e);
        }
        this.lambdaUpdate().eq(ServiceOrder::getOrderId, param.getOrderId())
                .set(ServiceOrder::getServState, ServiceState.NURSING).update();
        orderOperationService.save(new OrderOperation(param.getOrderId(), currentUser.getUserId(),
                currentUser.getUserRealname(), Operation.START, LocalDateTime.now()));
        return Result.success("请求成功");
    }

    @Override
    public Result endOrder(OrderParam param) {
        UserDTO currentUser = null;
        try {
            currentUser = systemUtils.getCurrentUser(param.getUserPhone());
        } catch (UnsupportedEncodingException e) {
            logger.info("获取当前对象失败");
            throw new RuntimeException(e);
        }
        this.lambdaUpdate().eq(ServiceOrder::getOrderId, param.getOrderId())
                .set(ServiceOrder::getServState, ServiceState.TO_EVALUATED).update();
        orderOperationService.save(new OrderOperation(param.getOrderId(), currentUser.getUserId(),
                currentUser.getUserRealname(), Operation.END, LocalDateTime.now()));
        UpdateWrapper<CareProviderInfo> wrapper = new UpdateWrapper<>();
        String servProvId = this.lambdaQuery().eq(ServiceOrder::getOrderId, param.getOrderId())
                .one().getServProvId();
        wrapper.eq("user_id", servProvId);
        wrapper.setSql("order_num = order_num + 1");
        careProviderInfoService.update(wrapper);
        return Result.success("请求成功");
    }

    @Override
    public Result evaluateOrder(EvaluateParams param) {
        UserDTO currentUser = null;
        try {
            currentUser = systemUtils.getCurrentUser(param.getUserPhone());
        } catch (UnsupportedEncodingException e) {
            logger.info("获取当前对象失败");
            throw new RuntimeException(e);
        }
        this.lambdaUpdate().eq(ServiceOrder::getOrderId, param.getOrderId())
                .set(ServiceOrder::getStars, param.getStars())
                .set(ServiceOrder::getServState, ServiceState.FINISH).update();
        //获取该订单服务提供者ID
        String servProvId = this.lambdaQuery().eq(ServiceOrder::getOrderId, param.getOrderId())
                .one().getServProvId();
        CareProviderInfo careProvider = careProviderInfoService.lambdaQuery().eq(CareProviderInfo::getUserId, servProvId).one();
        BigDecimal reviewsNum = new BigDecimal(careProvider.getReviewsNum());
        BigDecimal stars = new BigDecimal(param.stars);
        BigDecimal totalScore = reviewsNum.multiply(careProvider.getUserScore()).add(stars);
        BigDecimal newAverage = totalScore.divide(reviewsNum.add(BigDecimal.ONE), MathContext.DECIMAL128);
        UpdateWrapper<CareProviderInfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("user_id", servProvId);
        wrapper.setSql("reviews_num = reviews_num + 1, user_score = " + newAverage);
        careProviderInfoService.update(wrapper);
        if (param.stars <= 2) {
            wrapper.setSql("bad_reviews_ges = bad_reviews_ges + 1");
            careProviderInfoService.update(wrapper);
        }
        orderOperationService.save(new OrderOperation(param.getOrderId(), currentUser.getUserId(),
                currentUser.getUserRealname(), Operation.EVALUATED, LocalDateTime.now()));
        return Result.success("请求成功");
    }

    @Override
    public Result getOrderList(OrderListParams param) {
        Page<ServiceOrder> page = new Page<>();
        page.setSize(param.getSize());
        page.setCurrent(param.getPage());
        LambdaQueryWrapper<ServiceOrder> queryWrapper = new LambdaQueryWrapper<>();
        LocalDateTime[] orderTime = param.getOrderTime();

        queryWrapper.eq(StringUtils.isNotEmpty(param.getOrderStatus()), ServiceOrder::getServState, param.getOrderStatus())
                .ge(orderTime != null, ServiceOrder::getStartTime, orderTime != null ? orderTime[0] : null)
                .le(orderTime != null, ServiceOrder::getEndTime, orderTime != null ? orderTime[1] : null);
        // 使用 queryWrapper 直接调用 page 方法
        page = this.page(page, queryWrapper);
        return Result.success("请求成功", page);

    }

    @Override
    public Result createOrder(OrderParam param) throws UnsupportedEncodingException {
        UserDTO currentUser = systemUtils.getCurrentUser(param.getUserPhone());
        ServiceOrder serviceOrder = new ServiceOrder();
        String uuid = UUIDUtils.get24UUID();
        BeanUtils.copyProperties(param, serviceOrder);
        serviceOrder.setOrderId(uuid);
        serviceOrder.setServState(ServiceState.PENDING);
        serviceOrder.setCreateTime(LocalDateTime.now());
        if (currentUser.getUserType() == 1) {
            //老年人用户创建订单
            serviceOrder.setElderlyId(currentUser.getUserId());
            serviceOrder.setElderlyName(currentUser.getUserRealname());
            String associatedGuardiansId = elderService.lambdaQuery().eq(ElderState::getUserId, currentUser.getUserId()).one().getAssociatedGuardiansId();
            GuardianInfo guardianInfo = guardianService.lambdaQuery().eq(GuardianInfo::getUserId, associatedGuardiansId).one();
            serviceOrder.setGuardianId(guardianInfo.getUserId());
            serviceOrder.setGuardianName(guardianInfo.getUserRealname());
        } else if (currentUser.getUserType() == 2) {
            //监护人用户创建订单
            serviceOrder.setGuardianId(currentUser.getUserId());
            serviceOrder.setGuardianName(currentUser.getUserRealname());
            GuardianInfo guardianInfo = guardianService.lambdaQuery().eq(GuardianInfo::getUserId, currentUser.getUserId()).one();
            serviceOrder.setElderlyId(guardianInfo.getWardId());
            serviceOrder.setElderlyName(guardianInfo.getWardName());
        }
        this.save(serviceOrder);
        OrderOperation orderOperation = new OrderOperation();
        orderOperation.setOrderId(uuid);
        orderOperation.setOperatorId(currentUser.getUserId());
        orderOperation.setOperatorName(currentUser.getUserRealname());
        orderOperation.setOperationTime(LocalDateTime.now());
        orderOperation.setOperationState(Operation.CREATE);
        orderOperationService.save(orderOperation);
        return Result.success("创建成功");
    }

    @Override
    public Result getOrder(String orderId) {
        ServiceOrder orderInfo = this.lambdaQuery().eq(ServiceOrder::getOrderId, orderId).one();
        return Result.success("请求成功",orderInfo);
    }
}