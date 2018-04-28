package com.gzcb.creditcard.job;

import com.gzcb.creditcard.utils.ResponseUtil;
import com.gzcb.creditcard.vo.JobVo;
import com.gzcb.creditcard.websocket.IdoWebSocket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageOne {

    static Logger logger = LoggerFactory.getLogger(MessageOne.class);

    public static void pushMessage(JobVo jobVo)throws Exception{
        logger.info("发送所有的非定时({})推送信息!",jobVo.getMobile());
        IdoWebSocket.sendMessage(jobVo.getMobile(),ResponseUtil.succeed(jobVo).toJSONString());
    }
}
