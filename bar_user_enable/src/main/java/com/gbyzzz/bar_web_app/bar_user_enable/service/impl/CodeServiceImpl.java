package com.gbyzzz.bar_web_app.bar_user_enable.service.impl;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Code;
import com.gbyzzz.bar_web_app.bar_user_enable.repository.CodeRepository;
import com.gbyzzz.bar_web_app.bar_user_enable.service.CodeService;
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
    public Code addCode(String email) {
        Code code = new Code(email, generateCode());
        codeRepository.save(code);
        return code;
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
        return codeRepository.findById(a).get().getCode();
    }

    private Integer generateCode(){
        return (int) ((Math.random() * (MAX - MIN)) + MIN);
    }
}
