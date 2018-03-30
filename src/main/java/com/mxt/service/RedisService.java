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
@Configuration
public class RedisService {
    private static Map<Integer, StringRedisTemplate> redisTemplateMap = new HashMap<>();
    private static final String redisHost = "127.0.0.1";
    private static final int port = 6379;
    private static final String password = "";
    private static final int maxIdle = 8;
    private static final int maxWait = 1;
    private static final int maxActive = 8;
    private static final int minIdle = 0;

    @Bean
    public StringRedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(jackson2JsonRedisSerializer);
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashKeySerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);
        template.afterPropertiesSet();
        return template;
    }

    /**
     * 初始化模板
     * @param db
     * @return
     */
    public StringRedisTemplate redisTemplate(int db) {
        StringRedisTemplate template = new StringRedisTemplate();
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        connectionFactory.setDatabase(db);
        connectionFactory.setHostName(redisHost);
        connectionFactory.setPort(port);
        if (password != null && password.length() > 0) {
            connectionFactory.setPassword(password);
        }
        connectionFactory.setPoolConfig(poolConfig());
        template.setConnectionFactory(connectionFactory);
        return template;
    }

    /**
     * 连接池
     * @return
     */
    public JedisPoolConfig poolConfig() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        poolConfig.setMaxIdle(maxIdle);
        poolConfig.setMaxTotal(maxActive);
        poolConfig.setMaxWaitMillis(maxWait);
        poolConfig.setMinIdle(minIdle);
        return poolConfig;
    }

    /**
     * 获取模板
     * @param db
     * @return
     */
    public StringRedisTemplate getRedisTemplate(int db) {
        StringRedisTemplate stringRedisTemplate = redisTemplateMap.get(db);
        if (stringRedisTemplate == null) {
            stringRedisTemplate = redisTemplate(db);
            redisTemplateMap.put(db, stringRedisTemplate);
        }
        return stringRedisTemplate;
    }

    public void setValue(int db, String key, String value) {
        StringRedisTemplate redisTemplate = getRedisTemplate(db);
        redisTemplate.opsForValue().set(key, value);
    }

    public String getValue(int db, String key) {
        StringRedisTemplate redisTemplate = getRedisTemplate(db);
        return redisTemplate.opsForValue().get(key);
    }

}
