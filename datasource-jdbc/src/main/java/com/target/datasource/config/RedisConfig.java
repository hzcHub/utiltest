package com.target.datasource.config;


import com.target.datasource.bean.TrainInfo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;


import java.util.LinkedHashSet;
import java.util.List;

@Configuration
public class RedisConfig {


    @Bean(value = "redisTemplate")
    public RedisTemplate<Object, TrainInfo> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<Object, TrainInfo> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        Jackson2JsonRedisSerializer<TrainInfo> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<TrainInfo>(TrainInfo.class);
        template.setDefaultSerializer(jackson2JsonRedisSerializer);
        return template;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        return template;
    }


}
