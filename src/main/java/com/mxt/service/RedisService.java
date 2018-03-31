package com.mxt.service;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashMap;
import java.util.Map;

@Service
public class RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setValue(int db, String key, String value) {
        JedisConnectionFactory connectionFactory = (JedisConnectionFactory) stringRedisTemplate.getConnectionFactory();
        if (connectionFactory.getDatabase() != db) {
            connectionFactory.setDatabase(db);
            stringRedisTemplate.setConnectionFactory(connectionFactory);
        }
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public String getValue(int db, String key) {
        JedisConnectionFactory connectionFactory = (JedisConnectionFactory) stringRedisTemplate.getConnectionFactory();
        if (connectionFactory.getDatabase() != db) {
            connectionFactory.setDatabase(db);
            stringRedisTemplate.setConnectionFactory(connectionFactory);
        }
        return stringRedisTemplate.opsForValue().get(key);
    }
}
