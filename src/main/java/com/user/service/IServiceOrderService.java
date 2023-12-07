package com.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.OrderParam;
import com.user.pojo.Result;
import com.user.pojo.ServiceOrder;

/**
 * <p>
 * 服务订单 服务类
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
public interface IServiceOrderService extends IService<ServiceOrder> {

    Result<?> takeOrder(OrderParam param);
}
