package com.gbyzzz.bar_web_app.bar_email.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;
import java.util.Properties;

@Configuration
public class Config {
    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


    @Value("${gbyzzz.rabbitmq.input.queue}")
    private String inputQueueName;

    @Bean
    Queue inputQueue() {
        return new Queue(inputQueueName, false);
    }
}
//@Configuration
//public class Config implements WebMvcConfigurer {
//
//    private List<HttpMessageConverter<?>> converters;
//
//    public Config(List<HttpMessageConverter<?>> converters) {
//        this.converters = converters;
//    }
//
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        this.converters = converters;
//        converters.add(customJackson2JsonMessageConverter());
//        // Add other message converters if needed
//    }
//
//    @Bean
//    public MappingJackson2HttpMessageConverter customJackson2JsonMessageConverter() {
//        ObjectMapper objectMapper = new ObjectMapper();
//        // Customize your ObjectMapper as needed
//        // For example, you can configure date/time format, add custom serializers/deserializers, etc.
//        // Example configuration:
//        // objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
//        // objectMapper.registerModule(new MyCustomModule());
//
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        converter.setObjectMapper(objectMapper);
//        return converter;
//    }
//}