package com.user.service;

import com.user.pojo.Result;
import com.user.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.io.IOException;

/**
* @author ASUS
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2024-01-12 15:52:21
*/
public interface UserService extends IService<User> {


    Result certification(String realName, String idNumber, String userId) throws IOException;
}
