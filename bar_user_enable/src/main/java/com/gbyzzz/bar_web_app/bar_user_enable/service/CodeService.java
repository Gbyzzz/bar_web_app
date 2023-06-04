package com.gbyzzz.bar_web_app.bar_user_enable.service;

import com.gbyzzz.bar_web_app.bar_user_enable.entity.Code;

public interface CodeService {
    Code addCode(String email);
    boolean validateCode(Code code);

    Integer getCode(String a);
}
