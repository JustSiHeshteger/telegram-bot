package ru.zvrg.telegrambot.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.zvrg.telegrambot.listener.TelegramBotListener;

@Component
public class Initializer {

    private final TelegramBotListener listener;

    @Autowired
    public Initializer(TelegramBotListener listener) {
        this.listener = listener;
    }

    @EventListener({ContextRefreshedEvent.class})
    public void init() throws TelegramApiException {

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(listener);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
