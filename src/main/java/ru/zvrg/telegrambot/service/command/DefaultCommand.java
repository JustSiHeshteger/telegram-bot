package ru.zvrg.telegrambot.service.command;

import ru.zvrg.telegrambot.dto.Context;
import ru.zvrg.telegrambot.listener.TelegramBotListener;

import java.io.IOException;

public interface DefaultCommand {
    void executeCommand(Context context, TelegramBotListener telegramBotListener) throws IOException;
}
