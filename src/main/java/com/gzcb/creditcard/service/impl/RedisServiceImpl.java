package com.gzcb.creditcard.service.impl;

import com.gzcb.creditcard.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
@Service
@Transactional(rollbackFor = Exception.class)
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;
    @Override
    public void setCode(String key, String code) {
        /**
         * TimeUnit.SECONDS设置缓存时间600s
         */
        stringRedisTemplate.opsForValue().set(key,code,600, TimeUnit.SECONDS);
    }
    @Override
    public String getCode(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

    @Override
    public Object get(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    @Override
    public void set(String key, Object object) {
        /**
         * redis7天删除
         */
        redisTemplate.opsForValue().set(key,object,7, TimeUnit.DAYS);
//        redisTemplate.opsForValue().set(key,object);
    }

    @Override
    public void setList(String key, Object o) {
        redisTemplate.opsForList().rightPush(key,o);
    }

    @Override
    public Object getAll(String pattern) {
        return stringRedisTemplate.keys(pattern);
    }

}
