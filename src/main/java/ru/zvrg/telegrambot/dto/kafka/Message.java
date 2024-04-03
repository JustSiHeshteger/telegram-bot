package ru.zvrg.telegrambot.dto.kafka;

import lombok.Data;
import org.telegram.telegrambots.meta.api.objects.Update;

@Data
public class Message {

    //FIXME возможно в будущем изменить на Context
    /**
     * Параметры, которые пришли в сообщении телеграма
     */
    private Update update;

    /**
     * Запрос, который мы будем обрабатывать на сервисе БД
     */
    private String request;

    //FIXME возможно будет dto
    /**
     * Ответ, который мы должны получить (заполняется на стороне db-service)
     */
    private String response;
}
