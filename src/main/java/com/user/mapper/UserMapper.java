package com.user.mapper;

import com.user.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.mapstruct.Mapper;

/**
* @author ASUS
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-01-12 15:52:21
* @Entity com.user.pojo.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




