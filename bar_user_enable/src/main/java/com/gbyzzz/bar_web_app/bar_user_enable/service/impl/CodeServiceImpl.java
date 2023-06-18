package com.gbyzzz.bar_web_app.bar_user_enable.service.impl;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Code;
import com.gbyzzz.bar_web_app.bar_user_enable.messaging.entity.Message;
import com.gbyzzz.bar_web_app.bar_user_enable.repository.CodeRepository;
import com.gbyzzz.bar_web_app.bar_user_enable.service.CodeService;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CodeServiceImpl implements CodeService {

    private final static Integer MIN = 100000;
    private final static Integer MAX = 999999;


    private final CodeRepository codeRepository;

    public CodeServiceImpl(CodeRepository codeRepository) {
        this.codeRepository = codeRepository;
    }

    @Override
    public Message addCode(String email) {
        Code code = new Code(email, generateCode().toString());
        codeRepository.save(code);
        return new Message(code, "send code");
    }

    @Override
    public boolean validateCode(Code code) {
        Code codeFromDb = codeRepository.findById(code.getEmail()).orElseThrow();
        boolean valid = Objects.equals(codeFromDb.getCode(), code.getCode());
        if(valid){
            codeRepository.delete(code);
        }
        return Objects.equals(codeFromDb.getCode(), code.getCode());
    }

    @Override
    public Integer getCode(String a) {
        return (Integer) codeRepository.findById(a).get().getCode();
    }

    @Override
    public Message addRecoverCode(String email) {
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
        Code code = new Code(email, pass);
        codeRepository.save(code);
        return new Message(code, "recover");
    }

    private Integer generateCode(){
        return (int) ((Math.random() * (MAX - MIN)) + MIN);
    }
}
