package com.gbyzzz.bar_web_app.bar_email.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {
//
//    @Value("${application.kafka.topic.to_send_email}")
//    private String topicToSearch;
//
//    @Bean
//    public NewTopic toSearchTopic() {
//        return TopicBuilder.name(topicToSearch)
//                .partitions(1)
//                .replicas(1)
//                .build();
//    }
}
