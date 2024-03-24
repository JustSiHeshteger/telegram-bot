package ru.zvrg.telegrambot.service.command;

import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.service.command.impl.StartCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static ru.zvrg.telegrambot.utils.constants.Constants.START;

@Service
public class CommandExecutor {
    private static final Map<String, DefaultCommand> commands = new HashMap<>();

    static {
        commands.put(START, new StartCommand());
    }

    public DefaultCommand getCommand(String commandName) {
        return Optional.ofNullable(commands.get(commandName))
                .orElseThrow(() -> new IllegalArgumentException("Поступила неизвестная команда " + commandName));
    }
}
