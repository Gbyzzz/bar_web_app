package com.gbyzzz.bar_web_app.bar_backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gbyzzz.bar_web_app.bar_backend.entity.notification.Notification;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class NotificationWebSocketHandler extends TextWebSocketHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final List<WebSocketSession> sessions = new CopyOnWriteArrayList<>();

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) {
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
          sessions.add(session);
    }

    public void sendNotification(Notification notification) throws IOException {
        TextMessage message = new TextMessage(objectMapper.writeValueAsString(notification));
        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(message);
            }
        }
    }
}