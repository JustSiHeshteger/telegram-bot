package ru.zvrg.telegrambot.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.config.Config;
import ru.zvrg.telegrambot.service.command.CommandExecutor;

@Component
public class TelegramBotListener extends TelegramLongPollingBot {

    private final Config config;
    private final CommandExecutor commandExecutor;

    @Autowired
    public TelegramBotListener(Config config, CommandExecutor commandExecutor) {
        this.config = config;
        this.commandExecutor = commandExecutor;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            var chatId = update.getMessage().getChatId();

            commandExecutor.getCommand(messageText).executeCommand(update, this);
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
}
