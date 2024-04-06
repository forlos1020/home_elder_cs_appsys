package com.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.pojo.OrderOperation;
import com.user.service.IOrderOperationService;
import com.user.mapper.OrderOperationMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【order_operation(订单操作详细)】的数据库操作Service实现
* @createDate 2023-12-07 11:10:42
*/
@Service
@AllArgsConstructor
public class IOrderOperationServiceImpl extends ServiceImpl<OrderOperationMapper, OrderOperation>
    implements IOrderOperationService {

    @Override
    public void createTab() {
    }
}




