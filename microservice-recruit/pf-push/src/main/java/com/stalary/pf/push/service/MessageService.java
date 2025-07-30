package com.stalary.pf.push.service;

import com.alibaba.fastjson.JSONObject;
import com.stalary.pf.push.constant.PushConstants;
import com.stalary.pf.push.model.WsMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class MessageService {

    @Resource
    private WebSocketSender webSocketService;

    public void receiveMessage(String message, String channel) {
        log.info("receive message channel {}, message {}", channel, message);
        if (PushConstants.MESSAGE_CHANNEL.equals(channel)) {
            WsMessage wsMessage = JSONObject.parseObject(message, WsMessage.class);
            webSocketService.sendMessage(wsMessage.getUserId(), wsMessage.getMessage());
        } else if (PushConstants.CLOSE_CHANNEL.equals(channel)) {
            webSocketService.close(Long.valueOf(message));
        }
    }
}
