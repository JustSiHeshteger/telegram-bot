package ru.zvrg.telegrambot.config.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.TOPIC_NAME;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder
                .name(TOPIC_NAME)
                .build();

    }
}
