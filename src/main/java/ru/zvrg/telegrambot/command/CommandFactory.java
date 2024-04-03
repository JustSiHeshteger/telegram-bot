package ru.zvrg.telegrambot.command;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.botapimethods.BotApiMethodMessage;
import ru.zvrg.telegrambot.command.impl.StartCommand;
import ru.zvrg.telegrambot.command.impl.KafkaCommand;
import ru.zvrg.telegrambot.command.impl.ValuteCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.zvrg.telegrambot.utils.constants.Constants.Commands.*;

@Service
public class CommandFactory {
    private static final Map<String, DefaultCommand<? extends BotApiMethodMessage>> commands = new HashMap<>();

    public CommandFactory(StartCommand startCommand, ValuteCommand valuteCommand, KafkaCommand kafkaCommand) {
        commands.put(START, startCommand);
        commands.put(VALUTES, valuteCommand);
        commands.put(KAFKA, kafkaCommand);
    }

    public DefaultCommand<? extends BotApiMethodMessage> getCommand(String commandName) {
        return Optional.ofNullable(commands.get(commandName))
                .orElseThrow(() -> new IllegalArgumentException("Поступила неизвестная команда " + commandName));
    }
}
