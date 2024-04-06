package com.user.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPool;

/**
 * @author： lsh
 * @create： 2024-01-08 15:15
 */
@Configuration
public class RedisConfig {

    //创建GenericObjectPoolConfig对象，并自动绑定配置文件中的属性
    @Bean
    @ConfigurationProperties(prefix = "redis")
    public GenericObjectPoolConfig jedisPoolConfig() {
        return new GenericObjectPoolConfig();
    }

    //注入GenericObjectPoolConfig对象
    @Autowired
    GenericObjectPoolConfig jedisPoolConfig;

    @Bean
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(connectionFactory);

        // 使用Jackson2JsonRedisSerialize替换默认序列化
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        // 设置key和value的序列化规则
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();

        return redisTemplate;
    }

    //从配置文件中获取Redis服务器的地址、端口、密码等信息
    @Value("${redis.host}")
    private String host;
    @Value("${redis.port}")
    private int port;

    @Value("${redis.timeout}")
    private int timeout;

    //定义一个方法，用于创建JedisPool对象，并注册到Spring容器中
    @Bean
    public JedisPool jedisPool() {
        return new JedisPool(jedisPoolConfig, host, port, timeout);
    }
}
