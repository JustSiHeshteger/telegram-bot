package ru.zvrg.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.zvrg.telegrambot.dto.Context;

import java.io.IOException;

public interface DefaultCommand<Method extends BotApiMethodMessage> {
    Method executeCommand(Context context) throws IOException;
}
