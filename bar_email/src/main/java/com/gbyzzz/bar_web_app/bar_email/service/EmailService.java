package com.gbyzzz.bar_web_app.bar_email.service;

import com.gbyzzz.bar_web_app.bar_email.entity.Code;

public interface EmailService {

    void sendSimpleMessage(Code code);
}
