package ru.zvrg.telegrambot.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.zvrg.telegrambot.dto.Context;
import ru.zvrg.telegrambot.dto.Valute;
import ru.zvrg.telegrambot.service.ValuteService;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static ru.zvrg.telegrambot.utils.constants.Constants.DefaultValutes.MOST_POPULAR_VALUTES;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValuteCommand implements DefaultCommand<SendMessage> {

    private final ValuteService valuteService;

    @Override
    public SendMessage executeCommand(Context context) throws IOException {
        log.info("Выполнение команды /valutes для chatId = {}", context.getUpdate().getMessage().getChatId());

        return getAnswer(context);
    }

    private SendMessage getAnswer(Context context) throws IOException {
        final var list = valuteService.getValuteFromCbr();

        if (context.getParameters().isEmpty()) {
            context.setParameters(MOST_POPULAR_VALUTES);
        }
        final var selectedValutes = list.stream()
                .filter(valute -> context.getParameters().contains(valute.getCharCode()))
                .toList();
        log.info("найденная валюта {}", selectedValutes);

        return createMessage(context, selectedValutes);
    }

    private SendMessage createMessage(Context context, List<Valute> selectedValutes) {
        final StringBuilder answer = new StringBuilder();


        answer.append(LocalDate.now()).append(": ");
        for (var currValute: selectedValutes) {
            answer.append("\n").append(currValute.getNominal()).append(" ₽").append(" - ").
                    append(currValute.getValue()).append(" ").append(currValute.getCharCode());
            final double valueDifference = currValute.getValue() - currValute.getPrevious();
            if (valueDifference > 0) {
                answer.append(" ⬆").append(
                        String.format("%.3f", valueDifference));
            } else {
                answer.append(" ⬇").append(
                        String.format("%.3f", Math.abs(valueDifference)));
            }
        }

        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        message.setText(answer.toString());
        return message;
    }

}