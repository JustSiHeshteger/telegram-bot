package ru.zvrg.telegrambot.service.command.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.zvrg.telegrambot.dto.Valute;
import ru.zvrg.telegrambot.listener.TelegramBotListener;
import ru.zvrg.telegrambot.service.ValuteService;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
@Slf4j
public class ValuteCommand implements DefaultCommand {

    private ValuteService valuteService;

    @Override
    public void executeCommand(Update update, TelegramBotListener telegramBotListener) throws IOException {
        final var list = valuteService.getValuteFromCbr();
        try {
            telegramBotListener.execute(getAnswer(update, list));
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private SendMessage getAnswer(Update update, List<Valute> list) {
        final var params = update.getMessage().getText().split(" ");
        final StringBuilder answer = new StringBuilder();
        for (var currentValute: params){
            final var u = list.stream()
                    .filter(valute -> valute.getCharCode().equals(currentValute))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Не найдено нужной валюты = " + currentValute));
            log.info("найденная валюта {}", u);
            answer.append(LocalDate.now()).append(": ").append("\n").append(u.getValue()).append(" ").append(u.getCharCode()).append(" - ").append(u.getNominal()).append(" RUB\n");
        }

        //update.getMessage().getChat().getUserName();
        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(update.getMessage().getChatId()));
        message.setText(answer.toString());
        return message;
    }
}