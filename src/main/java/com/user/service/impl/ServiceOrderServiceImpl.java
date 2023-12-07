package com.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.ServiceOrderMapper;
import com.user.pojo.OrderParam;
import com.user.pojo.Result;
import com.user.pojo.ServiceOrder;
import com.user.service.IServiceOrderService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务订单 服务实现类
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
@Service
public class ServiceOrderServiceImpl extends ServiceImpl<ServiceOrderMapper, ServiceOrder> implements IServiceOrderService {

    @Override
    public Result<?> takeOrder(OrderParam param) {
        //TODO: 接单

        return null;
    }
}