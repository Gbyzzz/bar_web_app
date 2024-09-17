package com.gbyzzz.bar_web_app.bar_user_enable.service.impl;

import com.gbyzzz.bar_web_app.bar_user_enable.service.KafkaService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class KafkaServiceImpl implements KafkaService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public void sendMessage(String topic, Object message) {
        kafkaTemplate.send(topic, String.valueOf(System.currentTimeMillis()), message);
    }
}
