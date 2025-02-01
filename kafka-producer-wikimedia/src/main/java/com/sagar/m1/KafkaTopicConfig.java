package com.sagar.m1;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("${spring.kafka.wiki.kafka.name}")
    private String topicName;

    public NewTopic topic(){
        return TopicBuilder.name(topicName)
                .build();
    }
}
