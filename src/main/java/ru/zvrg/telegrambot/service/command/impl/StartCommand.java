package ru.zvrg.telegrambot.service.command.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zvrg.telegrambot.dto.Context;
import ru.zvrg.telegrambot.listener.TelegramBotListener;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

@Component
@RequiredArgsConstructor
public class StartCommand implements DefaultCommand {

    @Override
    public void executeCommand(Context context, TelegramBotListener telegramBotListener) {
        try {
            telegramBotListener.execute(getAnswer(context.getUpdate()));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private SendMessage getAnswer(Update update) {
        final String answer = "Hell0, " + update.getMessage().getChat().getUserName();
        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(answer);
        return message;
    }
}
