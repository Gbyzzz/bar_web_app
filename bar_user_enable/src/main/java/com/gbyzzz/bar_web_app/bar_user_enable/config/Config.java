package com.gbyzzz.bar_web_app.bar_user_enable.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class Config {

    @Value("${gbyzzz.rabbitmq.output.queue}")
    String outputEmailQueueName;

    @Value("${gbyzzz.rabbitmq.output.exchange}")
    String outputExchange;

    @Value("${gbyzzz.rabbitmq.output.routingkey}")
    private String outputEmailRoutingKey;

    @Value("${gbyzzz.rabbitmq.input.queue}")
    private String inputQueueName;

    @Bean
    Queue outputEmailQueue() {
        return new Queue(outputEmailQueueName, false);
    }

    @Bean
    Queue inputQueue() {
        return new Queue(inputQueueName, false);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange(outputExchange);
    }

    @Bean
    Binding bindingEmail(Queue outputEmailQueue, DirectExchange exchange) {
        return BindingBuilder.bind(outputEmailQueue).to(exchange).with(outputEmailRoutingKey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
