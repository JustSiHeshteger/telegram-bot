package ru.zvrg.telegrambot.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.zvrg.telegrambot.listener.TelegramBotListener;

@Component
@Slf4j
public class Initializer {

    private final TelegramBotListener listener;

    @Autowired
    public Initializer(TelegramBotListener listener) {
        this.listener = listener;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try {
            log.info("Начало создание бота {}", listener);
            final TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(listener);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
