package ru.zvrg.telegrambot.service.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.service.command.impl.StartCommand;
import ru.zvrg.telegrambot.service.command.impl.ValuteCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.zvrg.telegrambot.utils.constants.Constants.Commands.START;
import static ru.zvrg.telegrambot.utils.constants.Constants.Commands.VALUTES;

@Service
public class CommandFactory {
    private static final Map<String, DefaultCommand> commands = new HashMap<>();

    @Autowired
    public CommandFactory(StartCommand startCommand, ValuteCommand valuteCommand) {
        commands.put(START, startCommand);
        commands.put(VALUTES, valuteCommand);
    }

    public DefaultCommand getCommand(String commandName) {
        return Optional.ofNullable(commands.get(commandName))
                .orElseThrow(() -> new IllegalArgumentException("Поступила неизвестная команда " + commandName));
    }
}
