package com.user.controller;

import cn.hutool.core.util.SerializeUtil;
import com.user.pojo.Dto.UserDTO;
import com.user.pojo.param.LoginParam;
import com.user.pojo.Result;
import com.user.pojo.User;
import com.user.service.UserService;
import com.user.utils.ResultUtils;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author： lsh
 * @create： 2023-12-06 11:26
 */
@RestController
@RequestMapping("/account")
@AllArgsConstructor
public class AccountController {

    private final JedisPool jedisPool;

    private final UserService userService;
    private final StringRedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginParam param) {
        if (!param.getCode().equals(redisTemplate.opsForValue().get("captcha"))) {
            return ResultUtils.error(500, "验证码不正确或已过期");
        }
        List<User> list = userService.lambdaQuery().eq(User::getUserPhone, param.getPhone()).list();
        if (list.size() == 0) {
            return ResultUtils.error(500, "用户不存在");
        }
        User user = list.get(0);
        if (!DigestUtils.md5DigestAsHex(param.getPassword().getBytes()).equals(user.getUserPwd())) {
            return ResultUtils.error(500, "密码错误");
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userService.lambdaUpdate().eq(User::getUserId,user.getUserId()).set(User::getLastLoginTime,LocalDateTime.now()).update();
        Jedis jedis = jedisPool.getResource();
        jedis.set(("current_user_"+param.getPhone()).getBytes(StandardCharsets.UTF_8), SerializeUtil.serialize(userDTO));
        byte[] userByte = jedis.get(("current_user_" + param.getPhone()).getBytes(StandardCharsets.UTF_8));
        UserDTO currentUser = SerializeUtil.deserialize(userByte);
        logger.info(currentUser.toString());
        return ResultUtils.success();
    }


    @PostMapping("/register")
    public Result<?> register(@RequestBody LoginParam param) {
        String username = param.getPhone();
        String password = param.getPassword();
//        String code = param.getCode();
        User user = new User();
        user.setUserPhone(username);
        user.setUserPwd(DigestUtils.md5DigestAsHex(password.getBytes()));
        userService.save(user);
        return ResultUtils.success();
    }

}