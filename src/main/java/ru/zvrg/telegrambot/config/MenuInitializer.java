package ru.zvrg.telegrambot.config;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeDefault;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zvrg.telegrambot.listener.TelegramBotListener;

import java.util.ArrayList;
import java.util.List;

import static ru.zvrg.telegrambot.utils.constants.Constants.Commands.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class MenuInitializer {

    private final TelegramBotListener botListener;

    @PostConstruct
    private void initializeCommandsMenu() {
        //FIXME сделать так, чтоьы автоматически заполнялись все команды из CommandFactory
        final List<BotCommand> commands = new ArrayList<>();
        commands.add(new BotCommand(START, START_DESCRIPTION));
        commands.add(new BotCommand(VALUTES, VALUTES_DESCRIPTION));
        commands.add(new BotCommand(KAFKA, KAFKA_DESCRIPTION));

        try {
            botListener.execute(new SetMyCommands(commands, new BotCommandScopeDefault(), null));
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }
}
