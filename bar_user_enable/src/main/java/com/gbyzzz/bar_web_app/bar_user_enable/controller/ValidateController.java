package com.gbyzzz.bar_web_app.bar_user_enable.controller;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Message;
import com.gbyzzz.bar_web_app.bar_user_enable.service.CodeService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ValidateController {

    private final CodeService codeService;

    public ValidateController(CodeService codeService) {
        this.codeService = codeService;
    }

    @PostMapping("/validate")
    boolean validateEmail(@RequestBody Message message){
        return codeService.validateCode(message);
    }
    @PostMapping("/validate/pass")
    String getEmailFromCode(@RequestBody String code){
        return codeService.getEmailFromCode(code);
    }
}
