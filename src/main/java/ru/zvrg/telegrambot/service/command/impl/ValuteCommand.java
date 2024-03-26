package ru.zvrg.telegrambot.service.command.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.zvrg.telegrambot.listener.TelegramBotListener;
import ru.zvrg.telegrambot.service.ValuteService;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class ValuteCommand implements DefaultCommand {

    private ValuteService valuteService;

    @Override
    public void executeCommand(Update update, TelegramBotListener telegramBotListener) throws IOException {
        final var list = valuteService.getValuteFromCbr();
        //FIXME убрать потом
        final String usd = "USD";
        final var u = list.stream()
                .filter(valute -> valute.getCharCode().equals(usd))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найдено нужной валюты = " + usd));
        log.info("найденная валюта {}", u);
    }
}