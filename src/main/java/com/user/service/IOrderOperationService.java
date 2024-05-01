package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.OrderOperation;
import com.user.pojo.Result;

/**
* @author ASUS
* @description 针对表【order_operation(订单操作详细)】的数据库操作Service
* @createDate 2023-12-07 11:10:42
*/
public interface IOrderOperationService extends IService<OrderOperation> {


    Result getOrderStatus(String orderId);
}
