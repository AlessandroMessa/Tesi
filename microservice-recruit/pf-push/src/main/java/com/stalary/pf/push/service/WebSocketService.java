package com.stalary.pf.push.service;

import com.alibaba.fastjson.JSONObject;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.stalary.pf.push.constant.PushConstants;
import com.stalary.pf.push.model.WsMessage;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.TimeUnit;

@Service
@ServerEndpoint("/push/ws/{userId}")
@Slf4j
@Data
public class WebSocketService implements WebSocketSender {

    private static StringRedisTemplate redis;

    @Autowired
    public void setRedis(StringRedisTemplate stringRedisTemplate) {
        WebSocketService.redis = stringRedisTemplate;
    }

    private static Cache<Long, WebSocketService> sessionCache = CacheBuilder.newBuilder()
            .expireAfterWrite(1, TimeUnit.DAYS)
            .concurrencyLevel(8)
            .maximumSize(1000)
            .recordStats()
            .build();

    private Long userId;
    private Session session;
    private String message;

    @OnOpen
    public void onOpen(Session session, @PathParam("userId") Long userId) {
        log.info("userId: {} webSocket开始连接", userId);
        this.userId = userId;
        this.session = session;
        WebSocketService present = sessionCache.getIfPresent(userId);
        if (present != null && StringUtils.isNotEmpty(present.getMessage())) {
            present.setSession(this.session);
            this.message = present.getMessage();
            messageBroadcast(this.userId, this.message);
        } else {
            sessionCache.put(userId, this);
        }
        log.info("sessionCache: {}", sessionCache.asMap().keySet());
    }

    @OnClose
    public void onClose() {
        closeBroadcast(userId);
    }

    @OnError
    public void onError(Throwable e) {
        log.warn("webSocket error! ", e);
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("【webSocket】收到客户端发来的消息: {}", message);
    }

    @Override
    @SneakyThrows
    public void sendMessage(Long userId, String message) {
        WebSocketService socket = sessionCache.getIfPresent(userId);
        if (socket != null && socket.getSession() != null) {
            socket.session.getBasicRemote().sendText(message);
            socket.setMessage(message);
            sessionCache.put(userId, socket);
            log.info("【webSocket】 send message: userId: {}, message: {}", userId, message);
        } else {
            WebSocketService webSocketService = new WebSocketService();
            webSocketService.setUserId(userId);
            webSocketService.setMessage(message);
            sessionCache.put(userId, webSocketService);
            log.info("save: userId: {}, message: {}", userId, message);
        }
    }

    public void messageBroadcast(Long userId, String message) {
        log.info("message broadcast userId {}, message {}", userId, message);
        redis.convertAndSend(PushConstants.MESSAGE_CHANNEL, JSONObject.toJSONString(new WsMessage(userId, message)));
    }

    private void closeBroadcast(Long userId) {
        log.info("close broadcast userId {}", userId);
        redis.convertAndSend(PushConstants.CLOSE_CHANNEL, String.valueOf(userId));
    }

    @Override
    public void close(Long userId) {
        log.info("userId: {} webSocket关闭连接", userId);
        WebSocketService present = sessionCache.getIfPresent(userId);
        if (present != null) {
            present.setSession(null);
            sessionCache.put(userId, present);
        }
    }
}
