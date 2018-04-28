package com.gzcb.creditcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TJour;
//import com.gzcb.creditcard.redis.ClusterRedisTemplate;
import com.gzcb.creditcard.service.FriendService;
import com.gzcb.creditcard.service.JourService;
import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.JourVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "jour")
public class JourController {
    @Autowired
    JourService jourService;
    @Autowired
    FriendService friendService;

    /**
     * 半段字段是否为空
     *
     * @param string
     * @return
     */
    public boolean notEmpty(String string) {
        if (null != string && string != "") {
            return true;
        }
        return false;
    }

    /**
     * 分页查询jour
     *
     * @return
     */
    @RequestMapping(value = "getPageJour")
    @ResponseBody
    public JSONObject getPageJour(@RequestBody JourVO jourVO) {
        Page page = new Page<TJour>();
        if (null !=jourVO){
            if(null !=jourVO.getPageNum()){
                page.setPageNum(jourVO.getPageNum());
            }
            if(null !=jourVO.getPageSize()){
                page.setPageSize(jourVO.getPageSize());
            }
        }
        page = jourService.selectJour(page,jourVO.getMobile(),jourVO.getType());
        return ResponseUtil.succeed(page.toPageInfo());
    }

    /**
     * 获取好友的jour
     * @return
     */
    @RequestMapping(value = "getFriendJour")
    @ResponseBody
    public JSONObject getFriendJour(Integer userId){
        return ResponseUtil.succeed(friendService.selectAllJourByFriend(userId));
    }
    /**
     * 查询所有的jour
     *
     * @param createdBy
     * @return
     */
    @RequestMapping(value = "getAllJour")
    @ResponseBody
    public JSONObject getAllJour(Integer createdBy, Integer type) {
        List<TJour> list = new ArrayList<>();
        if (null != type&&1==type) {
            //只查看jour动态
            list = jourService.selectAllJour(createdBy, type);
        } else {
            //查看所有日记
            list = jourService.selectAllJour(createdBy, null);
        }
        return ResponseUtil.succeed(list);
    }
    
    /**
     * 更改jour的动态权限
     *
     * @param tJour
     * @return
     */
    @RequestMapping(value = "updateJour")
    @ResponseBody
    public JSONObject updateJour(TJour tJour) {

        return ResponseUtil.succeed();
    }

    /**
     * 根据id查询jour
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getJour")
    @ResponseBody
    public JSONObject getJour(Integer id) {
        return ResponseUtil.succeed(jourService.selectJourById(id));
    }

    /**
     * 添加jour
     *
     * @param tJour
     * @return
     */
    @RequestMapping(value = "addJour")
    @ResponseBody
    public JSONObject addJour(TJour tJour) {
        return ResponseUtil.succeed(jourService.addJour(tJour));
    }

    /**
     * 删除jour
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "delJour")
    @ResponseBody
    public JSONObject delJour(Integer id) {
        return ResponseUtil.succeed(jourService.deleterJour(id));
    }
}
