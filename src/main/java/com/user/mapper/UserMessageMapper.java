package com.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.user.pojo.UserMessage;
import org.apache.ibatis.annotations.Mapper;

/**
* @author ASUS
* @description 针对表【user_message(用户消息)】的数据库操作Mapper
* @createDate 2024-05-01 16:01:36
* @Entity generator.pojo.UserMessage
*/
@Mapper
public interface UserMessageMapper extends BaseMapper<UserMessage> {

}




