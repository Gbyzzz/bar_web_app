package com.gbyzzz.bar_web_app.bar_email.service.impl;

import com.gbyzzz.bar_web_app.bar_email.entity.Code;
import com.gbyzzz.bar_web_app.bar_email.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String SUBJECT = "Email confirmation";

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(Code code) {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(code.getEmail());
        message.setSubject(SUBJECT);
        message.setText(code.getCode().toString());
        emailSender.send(message);
    }
}
