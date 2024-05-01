package com.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.user.pojo.EmailCode;
import com.user.pojo.Result;
import com.user.pojo.User;
import com.user.service.EmailCodeService;
import com.user.mapper.EmailCodeMapper;
import com.user.service.UserService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;


@Service
@AllArgsConstructor
public class EmailCodeServiceImpl extends ServiceImpl<EmailCodeMapper, EmailCode>
        implements EmailCodeService {

    private static final int CODE_LENGTH = 5;
    @Autowired
    private JavaMailSender javaMailSender;

    private static final Logger logger = LoggerFactory.getLogger(EmailCodeService.class);

    @Override
    @Transactional
    public Result sendEmailCode(String email, Integer type) {
        String code = RandomStringUtils.random(CODE_LENGTH, true, true);
        sendCode(email, code);
        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setStatus(0);
        emailCode.setCreateTime(LocalDateTime.now());
        UpdateWrapper<EmailCode> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("status", 1).eq("status", 0).eq("email", email);
        this.update(updateWrapper);
        System.out.println(emailCode);
        this.save(emailCode);
        return Result.success("发送成功");
    }

    private void sendCode(String toEmail, String code) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("1604532728@qq.com");
            message.setTo(toEmail);
            message.setSubject("居家老人护理服务预约系统：");
            message.setText("您好，您的验证码是：" + code + ",五分钟内有效。");
            javaMailSender.send(message);
        } catch (Exception e) {
            logger.error("发送邮件失败", e);
            e.printStackTrace();
        }
    }

}




