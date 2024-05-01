package com.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.param.EvaluateParams;
import com.user.pojo.param.OrderListParams;
import com.user.pojo.param.OrderParam;
import com.user.pojo.Result;
import com.user.pojo.ServiceOrder;

import java.io.UnsupportedEncodingException;

/**
* @author ASUS
* @description 针对表【service_order(服务订单)】的数据库操作Service
* @createDate 2023-12-11 16:18:21
*/
public interface ServiceOrderService extends IService<ServiceOrder> {

    Result takeOrder(OrderParam param);

    Result confirmOrder(OrderParam param);

    Result startOrder(OrderParam param);

    Result endOrder(OrderParam param);

    Result evaluateOrder(EvaluateParams param);

    Result getOrderList(OrderListParams param);

    Result createOrder(OrderParam param) throws UnsupportedEncodingException;

    Result getOrder(String orderId);
}
