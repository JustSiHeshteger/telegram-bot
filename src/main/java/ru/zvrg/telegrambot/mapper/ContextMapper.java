package ru.zvrg.telegrambot.mapper;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.dto.Context;

import static ru.zvrg.telegrambot.utils.Utility.getCommandFromUpdate;

@Component
public class ContextMapper {
    public Context mapContext(Update update) {
        Context context = new Context(update);
        context.setCommand(getCommandFromUpdate(update.getMessage().getText()).orElse(null));
        context.setParameters(null);
        return context;
    }
}
