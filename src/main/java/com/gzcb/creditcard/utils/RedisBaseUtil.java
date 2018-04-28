package com.gzcb.creditcard.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Component
public class RedisBaseUtil {


    private JedisPool jedisPool;

    public Jedis getJedis(){
        Jedis jedis = jedisPool.getResource();
        if (jedis == null){
            return null;
        }
        return jedis;
    }

    public void releaseJedis(Jedis jedis){
        if (jedis == null){
            return;
        }
        jedis.close();
    }
}
