import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gzcb.creditcard.dao.UserMapper;
import com.gzcb.creditcard.dao.entities.User;
import com.gzcb.creditcard.job.RemarkJob;
import com.gzcb.creditcard.service.UserService;
import com.gzcb.creditcard.utils.CronDateUtils;
import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.RedisVo;
import org.junit.Test;
import org.quartz.Job;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test1 {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    public static void main(String[] args) {
        List list = new ArrayList();
        list.add("1");
        list.add("2");

        Map map = new HashMap();
        map.put("2","ddd");
        map.put("1","ccc");

        Object json = JSONObject.toJSON(list);

        Object json1 = JSONObject.toJSON(map);

        System.out.println(list);
        System.out.println(json);

        System.out.println(map);
        System.out.println("JSONObject"+json1);
        System.out.println("JSONUtils"+JSONUtils.toJSONString(map));
        System.out.println("JSON"+ JSON.toJSON(map));

        System.out.println(ResponseUtil.succeed(map));

        User user = new User();
        user.setMobile("123456");
        user.setId(1);
        user.setName("xys");
        System.out.println(JSON.toJSONString(user));
        System.out.println(JSONUtils.toJSONString(user));

    }

    @Test
    public void Test1()throws  Exception{
       String clazz1 = RemarkJob.class.getName();
        Class<? extends Job> clazz = (Class<? extends Job>) Class.forName(clazz1);
        System.out.println(clazz);
    }
    @Test
    public  void main1() {
        try {
            String host = "120.79.143.182";
            int port = 6379;
            Jedis jedis = new Jedis(host,port);
            jedis.auth("123xys");
            //ping通显示PONG
            System.out.println(jedis.ping());//去ping我们redis的主机所在ip和端口
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void cron() throws Exception {
        System.out.println(CronDateUtils.getAllDateStrByCron("0 */1 * * * ?",5));
    }


}
