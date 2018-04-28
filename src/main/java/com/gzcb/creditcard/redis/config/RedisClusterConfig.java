//package com.gzcb.creditcard.redis.config;
//
//import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.cache.annotation.EnableCaching;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;
//import redis.clients.jedis.HostAndPort;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// * 集群redis
// */
//@PropertySource("classpath:application.yml")
//@Configuration
//@EnableCaching
//public class RedisClusterConfig {
//
//    @Value("${myredis.pool.nodes}")
//    private String redisPool;
//    @Value("${myredis.pool.soTimeout}")
//    private Integer redisSoTimeOut;
//    @Value("${myredis.pool.connectionTimeout}")
//    private Integer redisConnectionTimeout;
//    @Value("${myredis.pool.maxAttempts}")
//    private Integer redisMaxAttempts;
//    @Value("${myredis.pool.passWord}")
//    private String redisPassword;
//
//    private RedisProperties.Sentinel sentinel;
//    private RedisProperties.Cluster cluster;
//
//    @Bean(name = "getJedisCluster")
//    public JedisCluster getJedisCluster(){
//        System.out.println(redisPool);
//        Set<HostAndPort> hostAndPortSet = new HashSet<>() ;
//            String[] hostAndPortSplit = redisPool.split(",");
//            GenericObjectPoolConfig config  = new GenericObjectPoolConfig();
//            for (String hostAndPort : hostAndPortSplit) {
//                String[] split = hostAndPort.split(":");
//                hostAndPortSet.add(new HostAndPort(split[0].trim(), Integer.parseInt(split[1].trim())));
//            }
//        return new JedisCluster(hostAndPortSet,redisConnectionTimeout,redisSoTimeOut,redisMaxAttempts,redisPassword,config);
//    }
//}
