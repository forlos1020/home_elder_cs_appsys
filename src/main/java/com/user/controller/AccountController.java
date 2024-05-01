package com.user.controller;

import cn.hutool.core.util.SerializeUtil;
import com.user.pojo.Dto.UserDTO;
import com.user.pojo.EmailCode;
import com.user.pojo.param.LoginParam;
import com.user.pojo.Result;
import com.user.pojo.User;
import com.user.service.EmailCodeService;
import com.user.service.UserService;
import com.user.utils.UUIDUtils;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
    private final EmailCodeService iEmailCodeService;

    private final StringRedisTemplate redisTemplate;
    private static final Logger logger = LoggerFactory.getLogger(AccountController.class);

    @PostMapping("/login")
    public Result login(@RequestBody LoginParam param, HttpServletRequest request) {
        User user = new User();
        if (!param.getCode().equals(redisTemplate.opsForValue().get("captcha"))) {
            return Result.error("验证码不正确或已过期");
        }
        if (param.getLoginType() == 1) {
            //密码登录
            List<User> list = userService.lambdaQuery().eq(User::getUserEmail, param.getEmail()).list();
            if (list.size() == 0) {
                return Result.error("用户不存在");
            }
            user = list.get(0);
            if (!DigestUtils.md5DigestAsHex(param.getPassword().getBytes()).equals(user.getUserPwd())) {
                return Result.error("密码错误");
            }
        } else if (param.getLoginType() == 2) {
            //邮箱登录
            List<User> list = userService.lambdaQuery().eq(User::getUserEmail, param.getEmail()).list();
            if (list.size() == 0) {
                return Result.error("用户不存在");
            }
            user = list.get(0);
            List<EmailCode> emailCodes = iEmailCodeService.lambdaQuery().eq(EmailCode::getEmail, param.getEmail()).eq(EmailCode::getStatus, 0).list();
            if (emailCodes != null) {
                EmailCode code = emailCodes.get(0);
                if (!param.getEmailCode().equals(code.getCode())) {
                    return Result.error("验证码错误");
                }
                iEmailCodeService.lambdaUpdate().eq(EmailCode::getEmail, param.getEmail())
                        .eq(EmailCode::getStatus, 0)
                        .set(EmailCode::getStatus, 1).update();
            } else {
                return Result.error("验证码错误");
            }
        } else if (param.getLoginType() == 3) {
            //手机号登录
            List<User> list = userService.lambdaQuery().eq(User::getUserPhone, param.getPhone()).list();
            if (list.size() == 0) {
                return Result.error("用户不存在");
            }
            user = list.get(0);
            if (!DigestUtils.md5DigestAsHex(param.getPassword().getBytes()).equals(user.getUserPwd())) {
                return Result.error("密码错误");
            }
        }
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        userService.lambdaUpdate().eq(User::getUserId, user.getUserId()).set(User::getLastLoginTime, LocalDateTime.now()).update();
        Jedis jedis = jedisPool.getResource();
        jedis.set(("current_user_" + param.getPhone()).getBytes(StandardCharsets.UTF_8), SerializeUtil.serialize(userDTO));
        HttpSession session = request.getSession();
        session.setAttribute("Login", userDTO);
        byte[] userByte = jedis.get(("current_user_" + param.getPhone()).getBytes(StandardCharsets.UTF_8));
        UserDTO currentUser = SerializeUtil.deserialize(userByte);
        logger.info(currentUser.toString());
        return Result.success("请求成功",userDTO);
    }


    @PostMapping("/register")
    public Result register(@RequestBody LoginParam param) {
        if (!param.getCode().equals(redisTemplate.opsForValue().get("captcha"))) {
            return Result.error("验证码不正确或已过期");
        }
        List<User> list = userService.lambdaQuery().eq(User::getUserPhone, param.getPhone())
                .or().eq(User::getUserEmail, param.getEmail()).list();
        if(list.size() != 0){
            return Result.error("用户已存在");
        }
        List<EmailCode> emailCodes = iEmailCodeService.lambdaQuery().eq(EmailCode::getEmail, param.getEmail()).eq(EmailCode::getStatus, 0).list();
        if (emailCodes != null) {
            EmailCode code = emailCodes.get(0);
            if (!param.getEmailCode().equals(code.getCode())) {
                return Result.error("验证码错误");
            }
            iEmailCodeService.lambdaUpdate().eq(EmailCode::getEmail, param.getEmail())
                    .eq(EmailCode::getStatus, 0)
                    .set(EmailCode::getStatus, 1).update();
        } else {
            return Result.error("验证码错误");
        }
        User user = new User();
        user.setUserId(UUIDUtils.get24UUID());
        user.setUserPhone(param.getPhone());
        user.setUserEmail(param.getEmail());
        user.setUserPwd(DigestUtils.md5DigestAsHex(param.getPassword().getBytes()));
        user.setCreateTime(LocalDateTime.now());
        user.setUserState(1);
        user.setUserType(param.getRegisterType());
        userService.save(user);
        return Result.success("请求成功");
    }

    @ApiOperation(value = "邮箱验证码获取")
    @PostMapping("/sendEmailCode")
    public Result sendEmailCode(@RequestParam String email, @RequestParam Integer type) {
        return iEmailCodeService.sendEmailCode(email, type);
    }


    @ApiOperation(value = "实名认证")
    @PostMapping("/certification")
    public Result certification(@RequestParam String realName, @RequestParam String idNumber, @RequestParam String userId) throws IOException {
        return userService.certification(realName, idNumber,userId);
    }
}