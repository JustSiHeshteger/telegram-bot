package ru.zvrg.telegrambot.listener.kafka;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.REQUEST_TOPIC_NAME;

@Slf4j
@Service
@AllArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        log.info("Метод sendMessage - попытка отправить сообщение в кафку");
        kafkaTemplate.send(REQUEST_TOPIC_NAME, message);
    }
}
