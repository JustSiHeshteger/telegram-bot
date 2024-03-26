package ru.zvrg.telegrambot.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.zvrg.telegrambot.listener.TelegramBotListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class Initializer {

    private final TelegramBotListener listener;

    @EventListener({ContextRefreshedEvent.class})
    public void init() {
        try {
            log.info("Запуск бота {}", listener.getBotUsername());
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(listener);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
