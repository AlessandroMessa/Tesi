package com.stalary.pf.push.service;


public interface WebSocketSender {
    void sendMessage(Long userId, String message);
    void close(Long userId);
}
