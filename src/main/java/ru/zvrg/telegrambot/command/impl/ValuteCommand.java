package ru.zvrg.telegrambot.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.zvrg.telegrambot.command.DefaultCommand;
import ru.zvrg.telegrambot.common.Context;
import ru.zvrg.telegrambot.common.Valute;
import ru.zvrg.telegrambot.service.ValuteService;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

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
        final String answer = selectedValutes.stream()
                .map(this::generateAnswer)
                .collect(Collectors.joining(System.lineSeparator()));

        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        message.setText(answer);
        return message;
    }

    private String generateAnswer(Valute valute) {
        final double valueDifference = valute.getValue() - valute.getPrevious();
        final String differenceSymbol = valueDifference > 0 ? " ⬆" : " ⬇";

        return String.format("%d ₽ - %.3f %s%s%.3f", valute.getNominal(), valute.getValue(),
                valute.getCharCode(), differenceSymbol, Math.abs(valueDifference));
    }
}