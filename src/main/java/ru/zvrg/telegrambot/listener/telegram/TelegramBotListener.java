package ru.zvrg.telegrambot.listener.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.config.Config;
import ru.zvrg.telegrambot.mapper.ContextMapper;
import ru.zvrg.telegrambot.command.CommandFactory;

@Slf4j
@Component
public class TelegramBotListener extends TelegramLongPollingBot {

    private final Config config;
    private final CommandFactory commandFactory;
    private final ContextMapper mapper;

    public TelegramBotListener(Config config, CommandFactory commandFactory, ContextMapper mapper) {
        super(config.getBotToken());
        this.config = config;
        this.commandFactory = commandFactory;
        this.mapper = mapper;
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            final var context = mapper.mapContext(update);
            try {
                this.execute(commandFactory.getCommand(context.getCommand()).executeCommand(context));
            } catch (Exception e) {
                log.error("Произошла ошибка {}", e.getMessage());
            }
        }
    }

    @Override
    public String getBotUsername() {
        return config.getBotName();
    }
}
