package ru.zvrg.telegrambot.service.command.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.common.Context;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

@Slf4j
@Component
public class StartCommand implements DefaultCommand<SendMessage> {

    @Override
    public SendMessage executeCommand(Context context) {
        log.info("Выполнение команды /start для chatId = {}", context.getUpdate().getMessage().getChatId());
        return getAnswer(context.getUpdate());
    }

    private SendMessage getAnswer(Update update) {
        final String answer = "Hell0, " + update.getMessage().getChat().getUserName();
        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(answer);
        return message;
    }
}
