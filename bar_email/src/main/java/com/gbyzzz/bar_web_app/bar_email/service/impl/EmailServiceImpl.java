package com.gbyzzz.bar_web_app.bar_email.service.impl;

import com.gbyzzz.bar_web_app.bar_email.entity.Code;
import com.gbyzzz.bar_web_app.bar_email.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class EmailServiceImpl implements EmailService {

    private static final String SUBJECT = "Email confirmation";

    private final JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(Code code) throws MessagingException, IOException {



        BufferedReader reader = new BufferedReader(new FileReader("src/test/resources/templates/mail.html"));
        StringBuilder stringBuilder = new StringBuilder();
        String line;
        String ls = System.getProperty("line.separator");
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
            stringBuilder.append(ls);
        }
// delete the last new line separator
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        reader.close();

        String mail = stringBuilder.toString();

        MimeMessage message = emailSender.createMimeMessage();
        mail = mail.replace("{{VERIFICATION_CODE}}", code.getCode().toString());




// use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
//        SimpleMailMessage message = new SimpleMailMessage();
        helper.setTo(code.getEmail());
        helper.setSubject(SUBJECT);

        helper.setText(mail, true);
        emailSender.send(message);
    }
}
