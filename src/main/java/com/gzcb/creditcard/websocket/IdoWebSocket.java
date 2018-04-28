package com.gzcb.creditcard.websocket;

import com.gzcb.creditcard.job.JobManager;
import com.gzcb.creditcard.utils.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tangliu
 */
@ServerEndpoint(value = "/ws/{mobile}", configurator = MyEndpointConfigure.class)
@Component
public class IdoWebSocket {
    private static Logger logger = LoggerFactory.getLogger(IdoWebSocket.class);

    private static HashMap<String, IdoWebSocket> userMap = new HashMap<>();


    private Session session;

    private static final String HELLO = "HELLO";

    @OnOpen
    public void onOpen(@PathParam("mobile") String mobile, Session session) {
        this.session = session;
        userMap.put(mobile, this);
        sendMessage(this, ResponseUtil.succeed(HELLO).toJSONString());
        logger.info("用户({})session({})建立连接", mobile,session.getId());
        JobManager.getJobManager().pushJobByMobile(mobile);
    }

    @OnClose
    public void onClose(Session session) throws Exception {
        Map<String, String> map = session.getPathParameters();
        userMap.remove(Integer.valueOf(map.get("mobile")));
        logger.info("用户({})断开连接", map.get("mobile"));
    }

    /**
     *
     * 发送消息给所有用户
     * @param message
     */
    public static void sendMessage(String message){
        userMap.forEach((mobile, socket)->{
            sendMessage(socket, message);
        });
    }

    /**
     * 发送消息
     */
    public static void sendMessage(IdoWebSocket socket, String message) {
        try {
            socket.session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            logger.error("推送信息出错", e);
        }
    }

    /**
     * 向特定用户推送消息
     *
     * @param mobile
     * @param message
     */
    public static void sendMessage(String mobile, String message) {
        IdoWebSocket socket = userMap.get(mobile);
        if (socket != null) {
            sendMessage(socket, message);
        }else {
            logger.error("socket为空，发送失败");
        }
    }
}
