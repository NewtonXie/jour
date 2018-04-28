package com.gzcb.creditcard.utils;

import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
public class RedisStringUtil extends RedisBaseUtil {

    public void putString(String key,String value){
        Jedis jedis = getJedis();
        try{
            if (jedis != null){
                jedis.set(key,value);
                jedis.expire(key,300);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            releaseJedis(jedis);
        }
    }

    public String getString(String key){
        Jedis jedis = getJedis();
        try {
            if (jedis != null){
                return jedis.get(key);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            releaseJedis(jedis);
        }
        return null;
    }
}
