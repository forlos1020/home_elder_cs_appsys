package com.user.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.user.pojo.UserConsumption;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【user_consumption(用户充值消费记录)】的数据库操作Mapper
* @createDate 2024-05-01 16:01:46
* @Entity generator.pojo.UserConsumption
*/
@Mapper
public interface UserConsumptionMapper extends BaseMapper<UserConsumption> {

}




