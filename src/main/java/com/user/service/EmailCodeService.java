package com.user.service;

import com.user.pojo.EmailCode;
import com.baomidou.mybatisplus.extension.service.IService;
import com.user.pojo.Result;

public interface EmailCodeService extends IService<EmailCode> {
    Result sendEmailCode(String email, Integer type);
}
