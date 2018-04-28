//import com.gzcb.creditcard.Application;
//import com.gzcb.creditcard.dao.entities.User;
////import com.gzcb.creditcard.redis.ClusterRedisTemplate;
//import com.gzcb.creditcard.service.JourService;
//import com.gzcb.creditcard.service.RedisService;
//import com.gzcb.creditcard.service.UserService;
//import com.gzcb.creditcard.utils.SerializableUtil;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//import redis.clients.jedis.JedisCluster;
//
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * redis 单机和集群的测试
// */
//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class SpringTest {
//    @Autowired
//    UserService userService;
////    @Autowired
////    ClusterRedisTemplate clusterRedisTemplate;
////    @Autowired
////    JedisCluster jedisCluster;
//    @Autowired
//    RedisService redisService;
//    @Autowired
//    private RedisProperties redisProperties;
//
//
//
//    @Test
//    public void test1(){
//        redisService.setCode("xys","开发者");
//        redisService.getCode("xys");
//    }
//
//    /**
//     * redis存储对象
//     * @throws Exception
//     */
////    @Test
////    public void test2() throws Exception {
////        System.out.println(redisProperties.getCluster());
////        jedisCluster.getClusterNodes().forEach((i,j)->{
////            System.out.println(i+"---->"+j.getNumIdle());
////        });
////        Map<byte[],byte[]> map = new HashMap<>();
////        User user = new User();
////        user.setName("xys");
////        user.setId(1);
////        map.put("1".getBytes(),SerializableUtil.serialize(user));
////        user.setId(2);
////        map.put("2".getBytes(),SerializableUtil.serialize(user));
////        jedisCluster.hmset("map".getBytes(),map);
////        map = jedisCluster.hgetAll("map".getBytes());
////        map.forEach((i,j)->{
////            try {
////                User user1 = (User) SerializableUtil.deserialize(j);
////                System.out.println(new String(i)+"-->"+user1.getId()+"---"+user1.getName());
////            } catch (Exception e) {
////                e.printStackTrace();
////            }
////        });
////    }
//}
