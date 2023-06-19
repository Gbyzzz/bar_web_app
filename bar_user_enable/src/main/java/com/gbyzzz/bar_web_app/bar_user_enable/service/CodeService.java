package com.gbyzzz.bar_web_app.bar_user_enable.service;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Code;
import com.gbyzzz.bar_web_app.bar_user_enable.messaging.entity.Message;

public interface CodeService {
    Message addCode(String email);
    boolean validateCode(Code code);

    Integer getCode(String a);

    Message addRecoverCode(String email);

    String getEmailFromCode(String code);
}
