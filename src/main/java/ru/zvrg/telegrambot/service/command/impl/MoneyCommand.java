package ru.zvrg.telegrambot.service.command.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zvrg.telegrambot.listener.TelegramBotListener;
import ru.zvrg.telegrambot.service.MoneyService;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

public class MoneyCommand implements DefaultCommand {

    private MoneyService moneyService;

    @Override
    public void executeCommand(Update update, TelegramBotListener telegramBotListener) {
        String answer = moneyService.getValuesFromCB();
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
