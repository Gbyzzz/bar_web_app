package com.gbyzzz.bar_web_app.bar_email.service.impl;

import com.gbyzzz.bar_web_app.bar_email.entity.Code;
import com.gbyzzz.bar_web_app.bar_email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
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


    private static final String SUBJECT = "Email confirmation";

    private final JavaMailSender emailSender;

    public EmailServiceImpl(ResourceLoader resourceLoader, JavaMailSender emailSender) {
        this.resourceLoader = resourceLoader;
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(Code code) throws MessagingException, IOException {
        Resource resource = resourceLoader.getResource("classpath:email/mail.html");
        String mail = new String(FileCopyUtils.copyToByteArray(resource.getInputStream()), StandardCharsets.UTF_8);

        MimeMessage message = emailSender.createMimeMessage();
        mail = mail.replace("{{VERIFICATION_CODE}}", code.getCode().toString());

        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(code.getEmail());
        helper.setSubject(SUBJECT);

        helper.setText(mail, true);
        emailSender.send(message);
    }
}
