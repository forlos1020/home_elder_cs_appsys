package com.user.utils;

import cn.hutool.core.util.SerializeUtil;
import com.user.pojo.Dto.UserDTO;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.UnsupportedEncodingException;

/**
 * @author： lsh
 * @create： 2024-01-09 14:39
 */
@AllArgsConstructor
@Component
public class SystemUtils {

    private JedisPool jedisPool;
    public UserDTO getCurrentUser(String userPhone) throws UnsupportedEncodingException {
        Jedis jedis = jedisPool.getResource();
        byte[] userByte = jedis.get(("current_user_"+userPhone).getBytes("UTF-8"));
        UserDTO currentUser = SerializeUtil.deserialize(userByte);
        return currentUser;
    }
}
