package ru.zvrg.telegrambot.service.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.zvrg.telegrambot.dto.Context;
import ru.zvrg.telegrambot.listener.kafka.KafkaProducer;
import ru.zvrg.telegrambot.service.ValuteService;
import ru.zvrg.telegrambot.service.command.DefaultCommand;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaCommand implements DefaultCommand<SendMessage> {

    private final KafkaProducer kafkaProducer;
    private final ValuteService valuteService;

    @Override
    public SendMessage executeCommand(Context context) throws IOException {
        log.info("Выполнение команды /kafka для chatId = {}", context.getUpdate().getMessage().getChatId());

        log.info("отправка сообщения в kafka");
        kafkaProducer.sendMessage(valuteService.getValuteFromCbr());

        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        message.setText("сообщение было отправлено");
        return message;
    }
}
