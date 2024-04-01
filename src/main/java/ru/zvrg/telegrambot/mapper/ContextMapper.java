package ru.zvrg.telegrambot.mapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.common.Context;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class ContextMapper {

    public Context mapContext(Update update) {
        final Context context = new Context(update);
        context.setCommand(getCommandFromUpdate(update.getMessage().getText()).orElse(null));
        context.setParameters(getParametersFromUpdate(update.getMessage().getText()));
        return context;
    }

    private Optional<String> getCommandFromUpdate(String text) {
        return Arrays.stream(text.split(" "))
                .findFirst();
    }

    private List<String> getParametersFromUpdate(String text) {
        return Arrays.stream(text.split(" "))
                .skip(1)
                .map(String::toUpperCase)
                .toList();
    }
}
