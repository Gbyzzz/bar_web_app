package com.gbyzzz.bar_web_app.bar_user_enable.messaging;

import com.gbyzzz.bar_web_app.bar_user_enable.service.CodeService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageConsumer {

    private final CodeService codeService;
    private final MessageProducer messageProducer;

    public MessageConsumer(CodeService codeService, MessageProducer messageProducer) {
        this.codeService = codeService;
        this.messageProducer = messageProducer;
    }

    @RabbitListener(queues = "${gbyzzz.rabbitmq.input.queue}")
    public void getMessage(String email) {
        messageProducer.sendToEmailService(codeService.addCode(email));
    }
}
