package com.user.controller;


import com.user.constance.Operation;
import com.user.constance.ServiceState;
import com.user.pojo.OrderOperation;
import com.user.pojo.OrderParam;
import com.user.pojo.Result;
import com.user.pojo.ServiceOrder;
import com.user.service.IOrderOperationService;
import com.user.service.IServiceOrderService;
import com.user.utils.ResultUtils;
import com.user.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    private IServiceOrderService orderService;

    private IOrderOperationService orderOperationService;
    @PostMapping("/createOrder")
    public Result<?> createOrder(@RequestBody OrderParam param) {
        ServiceOrder serviceOrder = new ServiceOrder();
        String uuid = UUIDUtils.get24UUID();
        BeanUtils.copyProperties(param, serviceOrder);
        serviceOrder.setOrderId(uuid);
        serviceOrder.setServState(ServiceState.PENDING);
        serviceOrder.setCreateTime(LocalDateTime.now());
        orderService.save(serviceOrder);
        OrderOperation orderOperation = new OrderOperation();
        orderOperation.setOrderId(uuid);
        orderOperation.setOperatorId(param.getElderlyId());
        orderOperation.setOperatorName(param.getElderlyName());
        orderOperation.setOperationTime(LocalDateTime.now());
        orderOperation.setOperationState(Operation.CREATE);
        orderOperationService.save(orderOperation);
        return ResultUtils.success();
    }

    @PostMapping("/takeOrder")
    public Result<?> takeOrder(@RequestBody OrderParam param) {
        return orderService.takeOrder(param);
    }

}
