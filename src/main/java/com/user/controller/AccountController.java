package com.user.controller;

import com.user.pojo.LoginParam;
import com.user.pojo.Result;
import com.user.pojo.User;
import com.user.service.impl.UserServiceImpl;
import com.user.utils.ResultUtils;
import com.user.utils.UUIDUtils;
import lombok.AllArgsConstructor;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author： lsh
 * @create： 2023-12-06 11:26
 */
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final UserServiceImpl userService;

    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginParam param) {
        User user = userService.lambdaQuery().eq(User::getUserName, param.getUsername()).one();
        if (user != null) {
            if (!DigestUtils.md5DigestAsHex(param.getPassword().getBytes()).equals(user.getUserPwd())) {
                return ResultUtils.error(500, "密码错误");
            }
        } else {
            return ResultUtils.error(500, "用户不存在");
        }
        return ResultUtils.success();
    }

    @PostMapping("/register")
    public Result<?> register(@RequestBody LoginParam param) {
        String username = param.getUsername();
        String password = param.getPassword();
        String code = param.getCode();
        User user = new User();
        user.setUserId(UUIDUtils.get16UUID());
        user.setUserName(username);
        user.setUserPwd(DigestUtils.md5DigestAsHex(password.getBytes()));
        userService.save(user);
        return ResultUtils.success();
    }

}
