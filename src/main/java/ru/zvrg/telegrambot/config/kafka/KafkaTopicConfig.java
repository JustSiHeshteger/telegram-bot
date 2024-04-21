package ru.zvrg.telegrambot.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.REQUEST_TOPIC_NAME;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(REQUEST_TOPIC_NAME)
                .build();
    }
}
