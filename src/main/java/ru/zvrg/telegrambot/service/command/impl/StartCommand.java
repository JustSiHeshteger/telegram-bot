package ru.zvrg.telegrambot.service.command.impl;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zvrg.telegrambot.listener.TelegramBotListener;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

public class StartCommand implements DefaultCommand {

    @Override
    public void executeCommand(Update update, TelegramBotListener telegramBotListener) {
        String answer = "Hell0, " + update.getMessage().getChat().getUserName();
        SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(answer);

        try {
            telegramBotListener.execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
