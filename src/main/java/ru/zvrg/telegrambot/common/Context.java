package ru.zvrg.telegrambot.common;

import lombok.Data;
import lombok.NonNull;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@Data
public class Context {

    @NonNull
    private Update update;

    private String command;

    private List<String> parameters;
}
