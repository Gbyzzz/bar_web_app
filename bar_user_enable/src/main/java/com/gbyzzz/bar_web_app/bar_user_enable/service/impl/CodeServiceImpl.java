package com.gbyzzz.bar_web_app.bar_user_enable.service.impl;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Message;
import com.gbyzzz.bar_web_app.bar_user_enable.service.CodeService;
import com.gbyzzz.bar_web_app.bar_user_enable.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final static Integer MIN = 100000;
    private final static Integer MAX = 999999;

    private final RedisTemplate<String, String> redisTemplate;
    private final KafkaService kafkaService;

    @KafkaListener(
            topics = "${application.kafka.topic.to_generate}",
            groupId = "groupId",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void addCode(Message message) {
        message.setCode(generateCode().toString());
        redisTemplate.opsForValue().set(message.getEmail(), message.getCode(), 86400L, TimeUnit.SECONDS);
//        codeRepository.save(message);
        kafkaService.sendMessage("to_send_email", message);
    }

    @KafkaListener(
            topics = "${application.kafka.topic.to_validate}",
            groupId = "groupId",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public boolean validateCode(Message message) {
        boolean valid = Objects.equals(redisTemplate.opsForValue().get(message.getEmail()), message.getCode());
        if(valid){
            redisTemplate.opsForValue().getAndDelete(message.getEmail());
        }
        return valid;
    }

    @Override
    public Integer getCode(String a) {
        return Integer.parseInt(Objects.requireNonNull(redisTemplate.opsForValue().get(a)));
    }

    @KafkaListener(
            topics = "${application.kafka.topic.to_recover}",
            groupId = "groupId",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void addRecoverCode(Message message) {
        RandomStringGenerator digitGenerator = new RandomStringGenerator.Builder()
                .withinRange('0', '9')
                .build();

        RandomStringGenerator lowercaseGenerator = new RandomStringGenerator.Builder()
                .withinRange('a', 'z')
                .build();

        RandomStringGenerator uppercaseGenerator = new RandomStringGenerator.Builder()
                .withinRange('A', 'Z')
                .build();

        StringBuilder codeBuilder = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            int rangeSelector = (int) Math.floor(Math.random() * 3);
            switch (rangeSelector) {
                case 0:
                    codeBuilder.append(digitGenerator.generate(1));
                    break;
                case 1:
                    codeBuilder.append(lowercaseGenerator.generate(1));
                    break;
                case 2:
                    codeBuilder.append(uppercaseGenerator.generate(1));
                    break;
            }
        }

        String pass = codeBuilder.toString();
//        String pass = pwdGenerator.generate(15);
//        Message message = new Message(pass, email);
        message.setCode(pass);
        redisTemplate.opsForValue().set(message.getCode(), message.getEmail(), 86400L, TimeUnit.SECONDS);

//        codeRepository.save(message);
        kafkaService.sendMessage("to_send_email", message);
    }

    @Override
    public String getEmailFromCode(String code) {
//        Message messageFromDb = codeRepository.findById(code).orElseThrow();
        return redisTemplate.opsForValue().get(code);
    }

    private Integer generateCode(){
        return (int) ((Math.random() * (MAX - MIN)) + MIN);
    }
}
