package com.gbyzzz.bar_web_app.bar_email.service;

public interface KafkaService {
    void sendMessage(String topic, Object message);
}
