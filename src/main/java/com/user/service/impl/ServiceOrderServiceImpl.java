package com.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.constance.Operation;
import com.user.constance.ServiceState;
import com.user.mapper.ServiceOrderMapper;
import com.user.pojo.CareProviderInfo;
import com.user.pojo.Dto.UserDTO;
import com.user.pojo.OrderOperation;
import com.user.pojo.param.EvaluateParams;
import com.user.pojo.param.OrderParam;
import com.user.pojo.Result;
import com.user.pojo.ServiceOrder;
import com.user.service.IOrderOperationService;
import com.user.service.ServiceOrderService;
import com.user.service.service.CareProviderInfoService;
import com.user.utils.ResultUtils;
import com.user.utils.SystemUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(ServiceOrderService.class);


    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result<?> takeOrder(OrderParam param) {
        UserDTO currentUser = null;
        try {
            currentUser = systemUtils.getCurrentUser(param.getUserPhone());
        } catch (UnsupportedEncodingException e) {
            logger.info("获取当前对象失败");
            throw new RuntimeException(e);
        }
        if (currentUser.getUserType() != 3) {
            return ResultUtils.error(500, "用户类型不匹配");
        }
        this.update(new LambdaUpdateWrapper<ServiceOrder>()
                .eq(ServiceOrder::getOrderId, param.getOrderId())
                .set(ServiceOrder::getServProvId, currentUser.getUserId())
                .set(ServiceOrder::getServProvName, currentUser.getUserRealname())
                .set(ServiceOrder::getServState, ServiceState.RECEIVED));
        orderOperationService.save(new OrderOperation(param.getOrderId(), currentUser.getUserId(),
                currentUser.getUserRealname(), Operation.RECEIVE, LocalDateTime.now()));

        return ResultUtils.success("请求成功");
    }

    @Override
    public Result<?> confirmOrder(OrderParam param) {
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
        return ResultUtils.success("请求成功");
    }

    @Override
    public Result<?> startOrder(OrderParam param) {
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
        return ResultUtils.success("请求成功");
    }

    @Override
    public Result<?> endOrder(OrderParam param) {
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
        return ResultUtils.success("请求成功");
    }

    @Override
    public Result<?> evaluateOrder(EvaluateParams param) {
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
        return ResultUtils.success("请求成功");
    }
}