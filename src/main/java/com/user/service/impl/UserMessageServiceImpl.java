package com.user.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.UserMessageMapper;
import com.user.pojo.UserMessage;
import com.user.service.UserMessageService;
import org.springframework.stereotype.Service;

/**
* @author ASUS
* @description 针对表【user_message(用户消息)】的数据库操作Service实现
* @createDate 2024-05-01 16:01:36
*/
@Service
public class UserMessageServiceImpl extends ServiceImpl<UserMessageMapper, UserMessage>
    implements UserMessageService {

}




