package com.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.UserConsumptionMapper;
import com.user.pojo.UserConsumption;
import com.user.service.UserConsumptionService;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【user_consumption(用户充值消费记录)】的数据库操作Service实现
* @createDate 2024-05-01 16:01:46
*/
@Service
public class UserConsumptionServiceImpl extends ServiceImpl<UserConsumptionMapper, UserConsumption>
    implements UserConsumptionService {

}




