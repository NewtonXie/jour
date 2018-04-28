package com.gzcb.creditcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.gzcb.creditcard.dao.entities.TUser;
import com.gzcb.creditcard.dao.entities.User;
//import com.gzcb.creditcard.redis.ClusterRedisTemplate;
import com.gzcb.creditcard.service.FriendService;
import com.gzcb.creditcard.service.RedisService;
import com.gzcb.creditcard.service.UserService;
import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.LoginVo;
import com.gzcb.creditcard.vo.RedisVo;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping(value = "user")
public class UserController {


    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    @Autowired
    RedisService redisService;
    public boolean notEmpty(String string){
        if (null!=string&&string!=""){
            return true;
        }
        return false;
    }
    /**
     * 登陆
     * @param loginVo
     * @return
     */
    @RequestMapping(value = "login")
    @ResponseBody
    public JSONObject login(LoginVo loginVo){
        TUser tUser = null;
        if (notEmpty(loginVo.getMobile())&&notEmpty(loginVo.getPassWord())){
            tUser = userService.selectUser(loginVo.getMobile(),loginVo.getPassWord());
        }
        if (null!=tUser){
            return ResponseUtil.succeed(tUser);
        }
        return ResponseUtil.failed("失败");
    }

    /**
     * 查询用户所有的好友
     * @return
     */
    @RequestMapping(value = "selectAllFriend")
    @ResponseBody
    public JSONObject selectAllFriend(Integer userId){
        List<TUser> tUsers  = new ArrayList<>();
        return ResponseUtil.succeed(userService.selectAllFriend(userId));
    }

    /**
     * 获取推送信息
     * @return
     */
    @RequestMapping(value = "getMessage")
    @ResponseBody
    public JSONObject getWebMessage(String mobile){
        List<RedisVo> redisVoList = (List<RedisVo>)redisService.get(mobile);
        return  ResponseUtil.succeed(redisVoList);
    }

    /**
     * 添加用户
     * @param tUser
     * @return
     */
    @RequestMapping(value = "addUser")
    @ResponseBody
    public JSONObject addUser(TUser tUser){
        int insert = 0;
        final boolean[] mobileFlage = {true};
        userService.selectAllUser().forEach(tUser1 -> {
            if(tUser1.getMobile().equals(tUser.getMobile())){
                mobileFlage[0] = false;
            }
        });
        if (mobileFlage[0] &&null!=tUser.getCreatedBy()&&notEmpty(tUser.getMobile())&&notEmpty(tUser.getPassword())){
            insert =  userService.addUser(tUser);
        }
        return ResponseUtil.succeed(insert);
    }

    @RequestMapping(value = "/demo",method = RequestMethod.GET)
    public String demo(Model model){
        model.addAttribute("name","ODS开发者");
        return "hello";
    }
}
