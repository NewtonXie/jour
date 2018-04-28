//package com.gzcb.creditcard.redis;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.JedisCluster;
//
///**
// * 自定义redisTemplate,包JedisCluster需要注意版本
// * <!--redis--> 的版本跟redis客户端不一样
//  <dependency>
//  <groupId>org.springframework.data</groupId>
//  <artifactId>spring-data-redis</artifactId>
//  </dependency>
//  <!--redis客户端-->
//  <dependency>
//  <groupId>redis.clients</groupId>
//  <artifactId>jedis</artifactId>
//  <version>2.9.0</version>
//  </dependency>
// */
//@Component
//public class ClusterRedisTemplate {
//    @Autowired
//    private JedisCluster jedisCluster;
//    @Autowired
//    private RedisProperties redisProperties;
//
//    private static final String KEY_SPLIT = ":";
//
//    private static Logger logger = LoggerFactory.getLogger(ClusterRedisTemplate.class);
//
//    /**
//     * 获取指定key的缓存
//     * @param prefix
//     * @param key
//     * @return
//     */
//    public String get(String prefix,String key){
//        return jedisCluster.get(prefix+KEY_SPLIT+key);
//    }
//    public String get(String key){
//        logger.info("{}",redisProperties.getCluster());
//        logger.info("{}",redisProperties.getPool());
//        return jedisCluster.get(key);
//    }
//    public void set(String key,String vlue){
//        logger.info("{}",redisProperties.getCluster());
//        logger.info("{}",redisProperties.getPool());
//        jedisCluster.set(key,vlue);
//    }
//}
