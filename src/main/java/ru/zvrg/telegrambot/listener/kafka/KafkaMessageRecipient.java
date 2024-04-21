package ru.zvrg.telegrambot.listener.kafka;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.dto.kafka.Message;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.GROUP_ID;
import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.RESPONSE_TOPIC_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaMessageRecipient {

    private final Gson gson;

    @KafkaListener(topics = RESPONSE_TOPIC_NAME, groupId = GROUP_ID)
    public void consume(String message) {
        log.info("Получено собщение {}", gson.fromJson(message, Message.class));
    }
}
