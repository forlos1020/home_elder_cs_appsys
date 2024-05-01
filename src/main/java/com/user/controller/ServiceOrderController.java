package com.user.controller;

import com.user.pojo.Result;
import com.user.pojo.param.EvaluateParams;
import com.user.pojo.param.OrderListParams;
import com.user.pojo.param.OrderParam;
import com.user.service.IOrderOperationService;
import com.user.service.ServiceOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

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
    private IOrderOperationService operationService;

    @PostMapping("/getOrderList")
    public Result getOrderList(@RequestBody OrderListParams param){
        return orderService.getOrderList(param);
    }
    @PostMapping("/getOrder")
    public Result getOrder(@RequestParam String orderId){
        return orderService.getOrder(orderId);
    }
    @PostMapping("/createOrder")
    public Result createOrder(@RequestBody OrderParam param) throws UnsupportedEncodingException {
        return orderService.createOrder(param);
    }

    @PostMapping("/takeOrder")
    public Result takeOrder(@RequestBody OrderParam param) {
        return orderService.takeOrder(param);
    }

    @PostMapping("/confirmOrder")
    public Result confirmOrder(@RequestBody OrderParam param) {
        return orderService.confirmOrder(param);
    }

    @PostMapping("/startOrder")
    public Result startOrder(@RequestBody OrderParam param) {
        return orderService.startOrder(param);
    }

    @PostMapping("/endOrder")
    public Result endOrder(@RequestBody OrderParam param) {
        return orderService.endOrder(param);
    }

    @PostMapping("/evaluateOrder")
    public Result evaluateOrder(@RequestBody EvaluateParams param) {
        return orderService.evaluateOrder(param);
    }

    @PostMapping("/getOrderStatus")
    public Result getOrderStatus(@RequestParam String orderId) {
        return operationService.getOrderStatus(orderId);
    }

}