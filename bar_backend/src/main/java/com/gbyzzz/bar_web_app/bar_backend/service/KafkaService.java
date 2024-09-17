package com.gbyzzz.bar_web_app.bar_backend.service;

public interface KafkaService {
    void sendMessage(String topic, Object message);
}
