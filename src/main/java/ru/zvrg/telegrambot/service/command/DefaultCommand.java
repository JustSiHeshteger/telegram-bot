package ru.zvrg.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.listener.TelegramBotListener;

import java.io.IOException;

public interface DefaultCommand {
    void executeCommand(Update update, TelegramBotListener telegramBotListener) throws IOException;
}
