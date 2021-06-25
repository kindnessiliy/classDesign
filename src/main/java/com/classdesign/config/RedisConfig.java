package com.classdesign.config;

import com.classdesign.domain.User;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author:zyh
 * @Time:2021-05-20-21:58
 * @email:1269231889@qq.com
 */
@EnableCaching
@Configuration
public class RedisConfig {
    @Bean
    public RedisTemplate<String, User> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String, User> template = new RedisTemplate<>();
        //关联工厂
        template.setConnectionFactory(factory);
        Jackson2JsonRedisSerializer<User> userJackson2JsonRedisSerializer =
                new Jackson2JsonRedisSerializer<>(User.class);
        //objectMapper转译
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        //enableDefaultTyping过期了，使用activeDefaultTyping。
        om.activateDefaultTyping(LaissezFaireSubTypeValidator.instance,
                ObjectMapper.DefaultTyping.NON_FINAL, JsonTypeInfo.As.WRAPPER_ARRAY);
        userJackson2JsonRedisSerializer.setObjectMapper(om);
        //设置key的序列化
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        //设置value的序列化
        template.setValueSerializer(userJackson2JsonRedisSerializer);
        template.setHashValueSerializer(userJackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }
}
