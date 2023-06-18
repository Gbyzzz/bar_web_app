package com.gbyzzz.bar_web_app.bar_email.messaging;


import com.gbyzzz.bar_web_app.bar_email.messaging.entity.Message;
import com.gbyzzz.bar_web_app.bar_email.service.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserEnableServiceConsumer {

    private final EmailService emailService;

    public UserEnableServiceConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${gbyzzz.rabbitmq.input.queue}")
    public void getMessage(Message message) throws MessagingException, IOException {
        emailService.sendSimpleMessage(message);
    }
}
