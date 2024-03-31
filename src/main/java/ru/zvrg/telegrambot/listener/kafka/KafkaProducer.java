package ru.zvrg.telegrambot.listener.kafka;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import ru.zvrg.telegrambot.dto.Valute;

import java.util.List;

import static ru.zvrg.telegrambot.utils.constants.Constants.KafkaSettings.TOPIC_NAME;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaProducer {

    private KafkaTemplate<String, Object> kafkaTemplate;
    private final Gson gson;

    public void sendMessage(List<Valute> valutes) {
        log.info("Метод sendMessage - попытка отправить сообщение в кафку");
        final String json = gson.toJson(valutes);
        kafkaTemplate.send(TOPIC_NAME, json);
    }
}
