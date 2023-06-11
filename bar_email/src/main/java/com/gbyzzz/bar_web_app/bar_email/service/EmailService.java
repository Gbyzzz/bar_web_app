package com.gbyzzz.bar_web_app.bar_email.service;

import com.gbyzzz.bar_web_app.bar_email.entity.Code;
import jakarta.mail.MessagingException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface EmailService {

    void sendSimpleMessage(Code code) throws MessagingException, IOException;
}
