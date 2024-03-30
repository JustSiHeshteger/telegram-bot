package ru.zvrg.telegrambot.service.command;

import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.zvrg.telegrambot.dto.Context;

import java.io.IOException;

public interface DefaultCommand<Message extends BotApiMethodMessage> {
    Message executeCommand(Context context) throws IOException;
}
