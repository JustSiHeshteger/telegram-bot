package ru.zvrg.telegrambot.listener.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.dto.kafka.Message;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.REQUEST_TOPIC_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Gson gson;

    public void sendMessage(Message message) {
        log.info("Метод sendMessage - попытка отправить сообщение в кафку");
        kafkaTemplate.send(REQUEST_TOPIC_NAME, gson.toJson(message));
    }
}
