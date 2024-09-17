package com.gbyzzz.bar_web_app.bar_email.service;

import com.gbyzzz.bar_web_app.bar_email.entity.Message;
import jakarta.mail.MessagingException;

import java.io.IOException;

public interface EmailService {

    void sendSimpleMessage(Message message) throws MessagingException, IOException;
}
