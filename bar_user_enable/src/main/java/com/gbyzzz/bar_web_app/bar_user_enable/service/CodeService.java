package com.gbyzzz.bar_web_app.bar_user_enable.service;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Message;


public interface CodeService {
    void addCode(Message message);
    boolean validateCode(Message message);

    Integer getCode(String a);

    void addRecoverCode(Message message);

    String getEmailFromCode(String code);
}
