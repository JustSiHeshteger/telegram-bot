package ru.zvrg.telegrambot.listener.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.GROUP_ID;
import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.RESPONSE_TOPIC_NAME;

@Slf4j
@Service
public class KafkaConsumer {

    @KafkaListener(topics = RESPONSE_TOPIC_NAME, groupId = GROUP_ID)
    public void consume(String message) {
        log.info("Получено собщение {}", message);
    }
}
