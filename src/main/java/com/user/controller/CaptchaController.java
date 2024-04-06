package com.user.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Author: LSH
 * @Date: 2023/6/13-06-13-15:56
 * @Description: com.SwordBBS.controller
 * @Version: 1.0
 */
@RestController
@AllArgsConstructor
public class CaptchaController {

    private DefaultKaptcha defaultKaptcha;
    private StringRedisTemplate redisTemplate;

    @GetMapping(value="/checkCode",produces = "image/jpeg")
    public void captcha(HttpServletRequest request, HttpServletResponse response){
        // 定义 response 输出类型为 image/jpeg 类型
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store,no-cache,must-revalidate");
        response.addHeader("Cache-Control", "post-check=0,pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        //生成验证码
        String text = defaultKaptcha.createText();
        redisTemplate.opsForValue().set("captcha",text,120, TimeUnit.SECONDS);
        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
