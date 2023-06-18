package com.gbyzzz.bar_web_app.bar_backend.messaging;

import com.gbyzzz.bar_web_app.bar_backend.messaging.entity.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class MessageProducer {

    private final AmqpTemplate rabbitTemplate;

    public MessageProducer(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${gbyzzz.rabbitmq.output.exchange}")
    private String exchange;

    @Value("${gbyzzz.rabbitmq.output.routingkey}")
    private String routingkey;

    public void generate(Message message) {
        rabbitTemplate.convertAndSend(exchange, routingkey, message);
    }
}
