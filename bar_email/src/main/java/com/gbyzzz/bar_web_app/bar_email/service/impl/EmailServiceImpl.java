package com.gbyzzz.bar_web_app.bar_email.service.impl;

import com.gbyzzz.bar_web_app.bar_email.entity.Code;
import com.gbyzzz.bar_web_app.bar_email.messaging.entity.Message;
import com.gbyzzz.bar_web_app.bar_email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;

@Service
public class EmailServiceImpl implements EmailService {
    private final ResourceLoader resourceLoader;


    private static final String SUBJECT_CONFIRM = "Email confirmation";
    private static final String RECOVER_PASSWORD = "Password Recovery";

    private final JavaMailSender emailSender;

    @Value("${gbyzzz.recover.url}")
    private String url;

    public EmailServiceImpl(ResourceLoader resourceLoader, JavaMailSender emailSender) {
        this.resourceLoader = resourceLoader;
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(Message message) throws MessagingException, IOException {
        Resource resource = resourceLoader.getResource("classpath:email/mail.html");
        String mail = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()),
                StandardCharsets.UTF_8);

        MimeMessage mimeMessage = emailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo((message.getObject()).getEmail());
        if(message.getInstructions().equals("send code")) {
            helper.setSubject(SUBJECT_CONFIRM);
            mail = mail.replace("{{CODE_OR_URL}}", message.getObject()
                    .getCode().toString()).replace("{{INSTRUCTIONS}}",
                    "Please use the verification code below on the Bar website:");
        } else {
            helper.setSubject(RECOVER_PASSWORD);
            mail = mail.replace("{{CODE_OR_URL}}", url + "?validate_code=" + message.getObject()
                    .getCode().toString()).replace("{{INSTRUCTIONS}}",
                    "Please go to the link below to recover you password on the Bar website:");
        }
        helper.setText(mail, true);
        emailSender.send(mimeMessage);
    }
}
