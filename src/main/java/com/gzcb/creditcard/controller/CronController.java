package com.gzcb.creditcard.controller;

import com.alibaba.fastjson.JSONObject;
import com.gzcb.creditcard.utils.CronDateUtils;
import com.gzcb.creditcard.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cron")
public class CronController {
    private static Logger logger = LoggerFactory.getLogger(CronController.class);
    /**
     * @param cron
     * @return
     */
    @RequestMapping(value = "cronComputeTime",method = {RequestMethod.GET})
    @ResponseBody
    public JSONObject cronComputeTime(@RequestParam String cron) {
        try {
            List<String> list = CronDateUtils.getAllDateStrByCron(cron, 5);
            if (list == null || list.isEmpty()) {
                return ResponseUtil.failed("cron表达式错误");
            }
            return ResponseUtil.succeed(list);
        } catch (Exception e) {
            logger.error("用户输入的cron表达式({})有误", cron);
            return ResponseUtil.failed("cron表达式错误");
        }
    }
}
