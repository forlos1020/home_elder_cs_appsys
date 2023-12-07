package com.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.mapper.UserMapper;
import com.user.pojo.User;
import com.user.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lsh
 * @since 2023-12-06
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
