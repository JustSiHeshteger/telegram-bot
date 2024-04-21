package ru.zvrg.telegrambot.listener.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Service;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.REQUEST_TOPIC_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageSender<T> {

    private final KafkaProducer<String, T> kafkaProducer;

    public void sendMessage(T message) {
        log.info("Метод sendMessage - попытка отправить сообщение в кафку");
        kafkaProducer.send(new ProducerRecord<>(REQUEST_TOPIC_NAME, message));
    }
}
