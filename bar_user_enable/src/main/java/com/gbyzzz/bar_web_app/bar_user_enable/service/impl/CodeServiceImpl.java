package com.gbyzzz.bar_web_app.bar_user_enable.service.impl;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Message;
import com.gbyzzz.bar_web_app.bar_user_enable.repository.CodeRepository;
import com.gbyzzz.bar_web_app.bar_user_enable.service.CodeService;
import com.gbyzzz.bar_web_app.bar_user_enable.service.KafkaService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {

    private final static Integer MIN = 100000;
    private final static Integer MAX = 999999;


    private final CodeRepository codeRepository;
    private final KafkaService kafkaService;

    @KafkaListener(
            topics = "${application.kafka.topic.to_generate}",
            groupId = "groupId",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void addCode(Message message) {
        message.setCode(generateCode().toString());
        codeRepository.save(message);
        kafkaService.sendMessage("to_send_email", message);
    }

    @KafkaListener(
            topics = "${application.kafka.topic.to_validate}",
            groupId = "groupId",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public boolean validateCode(Message message) {
        Message messageFromDb = codeRepository.findById(message.getEmail()).orElseThrow();
        boolean valid = Objects.equals(messageFromDb.getCode(), message.getCode());
        if(valid){
            codeRepository.delete(message);
        }
        return Objects.equals(messageFromDb.getCode(), message.getCode());
    }

    @Override
    public Integer getCode(String a) {
        return (Integer) codeRepository.findById(a).get().getCode();
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
        codeRepository.save(message);
        message.setCode(pass);
        kafkaService.sendMessage("to_send_email", message);
    }

    @Override
    public String getEmailFromCode(String code) {
        Message messageFromDb = codeRepository.findById(code).orElseThrow();
        return (String) messageFromDb.getCode();
    }

    private Integer generateCode(){
        return (int) ((Math.random() * (MAX - MIN)) + MIN);
    }
}
