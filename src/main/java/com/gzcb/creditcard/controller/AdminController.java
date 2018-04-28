package com.gzcb.creditcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.gzcb.creditcard.dao.entities.TContent;
import com.gzcb.creditcard.dao.entities.TJour;
import com.gzcb.creditcard.dao.entities.TUser;
import com.gzcb.creditcard.job.JobManager;
//import com.gzcb.creditcard.redis.ClusterRedisTemplate;
import com.gzcb.creditcard.service.ContentService;
import com.gzcb.creditcard.service.JourService;
import com.gzcb.creditcard.service.RedisService;
import com.gzcb.creditcard.service.UserService;
import com.gzcb.creditcard.utils.CronDateUtils;
import com.gzcb.creditcard.utils.JourException;
import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.JourVO;
import com.gzcb.creditcard.vo.MessageVo;
import com.gzcb.creditcard.vo.RedisVo;
import com.gzcb.creditcard.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @Autowired
    ContentService contentService;
    @Autowired
    RedisService redisService;
    @Autowired
    UserService userService;
    @Autowired
    JourService jourService;
//    @Autowired
//    private ClusterRedisTemplate clusterRedisTemplate;

    private final Logger logger = LoggerFactory.getLogger(AdminController.class);

    /**
     * 获取推送信息的分页列表
     *
     * @param messageVo
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMessagePage")
    public JSONObject getMessagePage(@RequestBody MessageVo messageVo) {
        Page page = new Page<TContent>();
        if (null != messageVo) {
            if (null != messageVo.getPageNum()) {
                page.setPageNum(messageVo.getPageNum());
            }
            if (null != messageVo.getPageSize()) {
                page.setPageSize(messageVo.getPageSize());
            }
        }
        page = contentService.getContentByPage(page, messageVo.getCreatedAt(), messageVo.getType());
        return ResponseUtil.succeed(page.toPageInfo());
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
        if (null != jourVO) {
            if (null != jourVO.getPageNum()) {
                page.setPageNum(jourVO.getPageNum());
            }
            if (null != jourVO.getPageSize()) {
                page.setPageSize(jourVO.getPageSize());
            }
        }
        System.out.println(page.toPageInfo());
        page = jourService.selectJour(page, jourVO.getMobile(), jourVO.getType());
        System.out.println(page.toPageInfo());
        return ResponseUtil.succeed(page.toPageInfo());
    }

    /**
     * 删除jour
     */
    @RequestMapping(value = "deleteJour")
    @ResponseBody
    public JSONObject deleteJour(Integer id) {
        return ResponseUtil.succeed(jourService.deleterJour(id));
    }

    /**
     * 将推送信息插入数据库
     *
     * @param tContent
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "push")
    public JSONObject push(TContent tContent,String mobile) {
        /**
         * 固定上传者
         */
        tContent.setCreatedBy(1);
        if (null!=userService.selectUser(mobile)){
            contentService.pushMessage(tContent);
        }
        return ResponseUtil.succeed();
    }

    /**
     * 前台点击推送单个消息，将其推送到redis,传进redis的形式是List<RedisVo>
     *
     * @param id
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "pushMessageToRedis")
    public JSONObject pushMessageToRedis(Integer id)throws JourException{
        TContent tContent = contentService.getContent(id);
        if (null!=tContent.getCron()){
            try {
                CronDateUtils.getAllDateStrByCron(tContent.getCron(),5);
            } catch (Exception e) {
                return ResponseUtil.failed();
            }
        }
        RedisVo redisVo = new RedisVo();
        if (null != tContent) {
            redisVo.setContent(tContent.getContent());
            redisVo.setTime(tContent.getCreatedAt());
            redisVo.setCron(tContent.getCron());
            redisVo.setName(tContent.getName());
        }
        /**
         * 推送给所有人
         */
        if (tContent.getType() == 0) {

            List<TUser> tUserList = userService.selectAllUser();
            tUserList.forEach(tUser -> {
                List<RedisVo> redisVoList = getMessage(tUser.getMobile());
                if (null == redisVoList) {
                    redisVoList = new ArrayList<>();
                    redisVoList.add(redisVo);
                } else {
                    redisVoList.add(redisVo);
                }
                redisService.set(tUser.getMobile(), redisVoList);
                /**
                 * 调用定时推送
                 */
                JobManager.getJobManager().pushJobByMobile(tUser.getMobile());
            });
        }
        /**
         * 推送给个人
         */
        if (tContent.getType() == 1) {
            String mobile = userService.selectUserById(tContent.getPushUserId()).getMobile();
            redisVo.setMobile(mobile);
            List<RedisVo> redisVoList = getMessage(mobile);
            if (null == redisVoList) {
                redisVoList = new ArrayList<>();
                redisVoList.add(redisVo);
            } else {
                redisVoList.add(redisVo);
            }
            redisService.set(mobile, redisVoList);
            /**
             * 调用定时推送
             */
            JobManager.getJobManager().pushJobByMobile(mobile);
        }

        tContent.setType((short) 2);
        return ResponseUtil.succeed(updateContent(tContent));
    }

    /**
     * 将所有的推送信息存进redis
     */
    @ResponseBody
    @RequestMapping(value = "getMessageToRedis")
    public void getMessageToRedis() {
        //推送类型为所有人
        List<TContent> tContentsAll = contentService.getMessage(Short.valueOf("0"));
        List<RedisVo> messageALL = new ArrayList();
        if (null != tContentsAll && tContentsAll.size() > 0) {
            tContentsAll.forEach(tContent -> {
                RedisVo redisVo = new RedisVo();
                redisVo.setContent(tContent.getContent());
                redisVo.setTime(tContent.getCreatedAt());
                redisVo.setName(tContent.getName());
                redisVo.setCron(tContent.getCron());
                redisVo.setId(tContent.getId());
                messageALL.add(redisVo);
            });
        }
        List<TUser> tUserList = userService.selectAllUser();
        tUserList.forEach(tUser -> {
            redisService.set(tUser.getMobile(), messageALL);
        });


        //推送类型为个人(包括了推送给所有人的信息)
        List<TContent> tContents = contentService.getMessage(Short.valueOf("1"));
        if (null != tContents && tContents.size() > 0) {
            tContents.forEach(tContent -> {
                List<RedisVo> message = new ArrayList();
                RedisVo redisVo = new RedisVo();
                redisVo.setContent(tContent.getContent());
                redisVo.setTime(tContent.getCreatedAt());
                redisVo.setName(tContent.getName());
                redisVo.setCron(tContent.getCron());
                redisVo.setId(tContent.getId());
                message.add(redisVo);
                String mobile = userService.selectUserById(tContent.getPushUserId()).getMobile();
                message.addAll(getMessage(mobile));
                redisService.set(mobile, message);
            });
        }
        /**
         * 推送过的信息更改type
         */
        updateContentType();
    }

    public List<RedisVo> getMessage(String mobile) {
        List<RedisVo> redisVoList = (List<RedisVo>) redisService.get(mobile);
        return redisVoList;
    }

    /**
     * 更改推送信息的type=2
     */
    public void updateContentType() {
        List<TContent> tContentsAll = contentService.getMessage(Short.valueOf("0"));
        tContentsAll.forEach(tContent -> {
            tContent.setType((short) 2);
            updateContent(tContent);
        });
        List<TContent> tContents = contentService.getMessage(Short.valueOf("1"));
        tContents.forEach(tContent -> {
            tContent.setType((short) 2);
            updateContent(tContent);
        });
    }

    /**
     * 更新推送信息
     *
     * @param tContent
     * @return
     */
    public int updateContent(TContent tContent) {
        return contentService.updateContent(tContent);
    }

    /**
     * 获取推送信息
     *
     * @param mobile
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getMessage")
    public JSONObject getWebMessage(String mobile) {
        List<RedisVo> redisVoList = (List<RedisVo>) redisService.get(mobile);
        return ResponseUtil.succeed(redisVoList);
    }

    /**
     * 获取日记上传人
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "selectJourCreatedBy")
    public JSONObject selectJourCreatedBy(Integer id){
        Integer createdBy = jourService.selectJourById(id).getCreatedBy();
        TUser tUser =  userService.selectUserById(createdBy);
        tUser.setPassword("");
        return ResponseUtil.succeed(tUser);
    }
    /**
     * 删除推送
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "deleteMessage")
    public JSONObject deleteMessage(Integer id) {
        return ResponseUtil.succeed(contentService.deleteContent(id));
    }

    /**
     * 获取用户列表
     *
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "getAllUser",method = {RequestMethod.POST})
    public JSONObject getAllUser(@RequestBody UserVo userVo) {
        Page page = new Page<TUser>();
        if (null != userVo) {
            if (null != userVo.getPageNum()) {
                page.setPageNum(userVo.getPageNum());
            }
            if (null != userVo.getPageSize()) {
                page.setPageSize(userVo.getPageSize());
            }
        }
        page = userService.selectAllUserByPage(page, userVo.getName(), userVo.getMobile());
        return ResponseUtil.succeed(page.toPageInfo());
    }

    @ResponseBody
    @RequestMapping(value = "updateUser")
    public JSONObject updateUser(Integer id) {
        logger.info("{}", id);
        TUser tUser = userService.selectUserById(id);
        if (tUser.getLocked() == 1) {
            tUser.setLocked((short) 0);
        } else {
            tUser.setLocked((short) 1);
        }
        return ResponseUtil.succeed(userService.updateUser(tUser));
    }

    /**
     * redis集群
     *
     * @return
     */
//    @RequestMapping(value = "clusterRedis")
//    @ResponseBody
//    public JSONObject clusterRedis() {
//        clusterRedisTemplate.set("cluster", "集群");
//        logger.info("clusterRedis{}", clusterRedisTemplate.get("xys"));
//        return ResponseUtil.succeed(clusterRedisTemplate.get("", "xys"));
//    }
}
