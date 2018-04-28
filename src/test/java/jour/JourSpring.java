//package jour;
//
//import com.github.pagehelper.Page;
//import com.gzcb.creditcard.Application;
//import com.gzcb.creditcard.controller.AdminController;
//import com.gzcb.creditcard.dao.TUserMapper;
//import com.gzcb.creditcard.dao.entities.TContent;
//import com.gzcb.creditcard.dao.entities.TJour;
//import com.gzcb.creditcard.dao.entities.TUser;
//import com.gzcb.creditcard.job.BaseJob;
//import com.gzcb.creditcard.job.RemarkJob;
//import com.gzcb.creditcard.service.*;
//import com.gzcb.creditcard.utils.ResponseUtil;
//import com.gzcb.creditcard.vo.RedisVo;
//import org.assertj.core.util.Strings;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.quartz.Job;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@WebAppConfiguration
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class JourSpring {
//    @Autowired
//    JourService jourService;
//    @Autowired
//    UserService userService;
//    @Autowired
//    FriendService friendService;
//    @Autowired
//    TUserMapper tUserMapper;
//    @Autowired
//    ContentService contentService;
//    @Autowired
//    RedisService redisService;
//    @Test
//    public void Test(){
////        System.out.println(jourService.deleterJour(12));
////        TJour tJour = new TJour();
////        tJour.setsSite("中国");
////        tJour.setJour("第二个");
////        tJour.setCreatedBy(1);
////        System.out.println(jourService.addJour(tJour));
////        System.out.println(jourService.selectJourById(1).getJour());
//        Page<TJour> page = new Page<>();
//        page.setPageNum(1);
//        page.setPageSize(10);
//        System.out.println(jourService.selectJour(page,null,null));
////        System.out.println(jourService.selectAllJour(1,0).size());
//    }
//
//    @Test
//    public void Test2(){
////        TUser tUser = new TUser();
////        tUser.setMobile("15797828481");
////        tUser.setPassword("1234567");
////        tUser.setCreatedBy(1);
////        System.out.println(userService.addUser(tUser));
//        System.out.println(ResponseUtil.succeed(userService.selectUser("15797828481","1234567")));
//    }
//    @Test
//    public void Test3(){
////        System.out.println(ResponseUtil.succeed(friendService.selectAllJourByFriend(1)));
////        System.out.println(ResponseUtil.succeed(friendService.selectAllFriend(1)));
//        System.out.println(ResponseUtil.succeed(null));
//    }
//    @Test
//    public void Test4(){
//        TContent tContent = new TContent();
//        tContent.setCreatedBy(1);
//        tContent.setContent("推送信息1");
//
//        System.out.println(contentService.pushMessage(tContent));
//        System.out.println(ResponseUtil.succeed(contentService.getMessage(Short.valueOf("1"))));
//    }
//    @Test
//    public void Test5(){
////        //推送类型为所有人
////        List<TContent> tContentsAll =  contentService.getMessage(Short.valueOf("0"));
////        List<RedisVo> messageALL = new ArrayList();
////        tContentsAll.forEach(tContent -> {
////            RedisVo redisVo = new RedisVo();
////            redisVo.setContent(tContent.getContent());
////            redisVo.setTime(tContent.getCreatedAt());
////            messageALL.add(redisVo);
////        });
////        List<TUser> tUserList = userService.selectAllUser();
////        tUserList.forEach(tUser -> {
////            redisService.set(tUser.getMobile(),messageALL);
////        });
////
////        //推送类型为个人
////        List<TContent> tContents =  contentService.getMessage(Short.valueOf("1"));
////        tContents.forEach(tContent -> {
////            List<RedisVo> message = new ArrayList();
////            RedisVo redisVo = new RedisVo();
////            redisVo.setContent(tContent.getContent());
////            redisVo.setTime(tContent.getCreatedAt());
////            message.add(redisVo);
////            String mobile = userService.selectUserById(tContent.getPushUserId()).getMobile();
////            message.addAll(getMessage(mobile));
////            redisService.set(mobile,message);
////        });
//        List<RedisVo> redisVoList =  (List<RedisVo>)redisService.get("123");
//
//        if(null!=redisVoList) {
//            redisVoList.forEach(redisVo -> {
//                System.out.println(redisVo.getContent());
//            });
//        }
//
//        List<TContent> tContentsAll = contentService.getMessage(Short.valueOf("0"));
//        tContentsAll.forEach(tContent -> {
//            tContent.setType((short) 3);
//            updateContent(tContent);
//        });
//        List<TContent> tContents =  contentService.getMessage(Short.valueOf("1"));
//        tContents.forEach(tContent -> {
//            tContent.setType((short) 3);
//            updateContent(tContent);
//        });
//
//    }
//    public int updateContent(TContent tContent){
//        return contentService.updateContent(tContent);
//    }
//    public List<RedisVo> getMessage(String mobile){
//        List<RedisVo> redisVoList = (List<RedisVo>)redisService.get(mobile);
//        return redisVoList;
//    }
//    @Test
//    public void Test6(){
//        Page<TUser> page = new Page<>();
//        page.setPageNum(1);
//        page.setPageSize(15);
//    }
//    @Test
//    public void Test7(){
//        List<String> strings = new ArrayList<>();
//        List<RedisVo> redisVoList = getMessage("1232");
//        System.out.println(ResponseUtil.succeed(redisVoList));
////        strings = null;
//        strings.add(null);
//    }
//    @Test
//    public void Test8(){
//        System.out.println("this::"+this.getClass().getSimpleName());
//        List<TContent> tContents = contentService.getMessage(null);
//        System.out.println(ResponseUtil.succeed(tContents));
//        List<TJour> tJours = jourService.selectAllJour(null,null);
//        System.out.println(ResponseUtil.succeed(tJours.size()));
//    }
//    @Test
//    public void Tets9() throws ClassNotFoundException {
//        String  s = BaseJob.class.toString();
//        System.out.println(s);
//        Class<? extends Job> clazz = (Class<? extends Job>) Class.forName("com.gzcb.creditcard.job.JobManager");
//        System.out.println(clazz);
//    }
////    @Test
////    public void Test10(){
////        List<RedisVo> redisVoList = (List<RedisVo>) redisService.get("123");
////        System.out.println(redisVoList.isEmpty());
//////        System.out.println(ResponseUtil.succeed(redisVoList));
////    }
//}
