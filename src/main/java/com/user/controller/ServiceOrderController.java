package com.user.controller;

import com.user.constance.Operation;
import com.user.constance.ServiceState;
import com.user.pojo.*;
import com.user.pojo.Dto.UserDTO;
import com.user.pojo.param.EvaluateParams;
import com.user.pojo.param.OrderParam;
import com.user.service.IOrderOperationService;
import com.user.service.ServiceOrderService;
import com.user.service.UserService;
import com.user.service.service.ElderStateService;
import com.user.service.service.GuardianInfoService;
import com.user.utils.ResultUtils;
import com.user.utils.SystemUtils;
import com.user.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;

/**
 * <p>
 * 服务订单 前端控制器
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class ServiceOrderController {

    private ServiceOrderService orderService;

    private IOrderOperationService orderOperationService;
    private UserService userService;
    private GuardianInfoService guardianService;
    private ElderStateService elderService;

    private SystemUtils systemUtils;

    @PostMapping("/createOrder")
    public Result<?> createOrder(@RequestBody OrderParam param) throws UnsupportedEncodingException {
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
        orderService.save(serviceOrder);
        OrderOperation orderOperation = new OrderOperation();
        orderOperation.setOrderId(uuid);
        orderOperation.setOperatorId(currentUser.getUserId());
        orderOperation.setOperatorName(currentUser.getUserRealname());
        orderOperation.setOperationTime(LocalDateTime.now());
        orderOperation.setOperationState(Operation.CREATE);
        orderOperationService.save(orderOperation);
        return ResultUtils.success();
    }

    @PostMapping("/takeOrder")
    public Result<?> takeOrder(@RequestBody OrderParam param) {
        return orderService.takeOrder(param);
    }

    @PostMapping("/confirmOrder")
    public Result<?> confirmOrder(@RequestBody OrderParam param) {
        return orderService.confirmOrder(param);
    }

    @PostMapping("/startOrder")
    public Result<?> startOrder(@RequestBody OrderParam param) {
        return orderService.startOrder(param);
    }

    @PostMapping("/endOrder")
    public Result<?> endOrder(@RequestBody OrderParam param) {
        return orderService.endOrder(param);
    }

    @PostMapping("/evaluateOrder")
    public Result<?> evaluateOrder(@RequestBody EvaluateParams param) {
        return orderService.evaluateOrder(param);
    }

}