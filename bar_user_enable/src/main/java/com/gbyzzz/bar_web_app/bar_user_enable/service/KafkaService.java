package com.gbyzzz.bar_web_app.bar_user_enable.service;

public interface KafkaService {
    void sendMessage(String topic, Object message);
}
