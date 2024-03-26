package ru.zvrg.telegrambot.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.config.Config;
import ru.zvrg.telegrambot.service.command.CommandFactory;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class TelegramBotListener extends TelegramLongPollingBot {

    private final Config config;
    private final CommandFactory commandFactory;

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();

            try {
                commandFactory.getCommand(messageText).executeCommand(update, this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }

    @Override
    public String getBotToken() {
        return config.getBotToken();
    }
}
