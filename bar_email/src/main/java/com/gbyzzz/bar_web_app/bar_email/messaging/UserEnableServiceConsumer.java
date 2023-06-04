package com.gbyzzz.bar_web_app.bar_email.messaging;


import com.gbyzzz.bar_web_app.bar_email.entity.Code;
import com.gbyzzz.bar_web_app.bar_email.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserEnableServiceConsumer {

    private final EmailService emailService;

    public UserEnableServiceConsumer(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${gbyzzz.rabbitmq.input.queue}")
    public void getMessage(Code code) {
        emailService.sendSimpleMessage(code);
    }
}
