package ru.zvrg.telegrambot.command.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import ru.zvrg.telegrambot.command.DefaultCommand;
import ru.zvrg.telegrambot.common.Context;
import ru.zvrg.telegrambot.dto.kafka.Message;
import ru.zvrg.telegrambot.listener.kafka.KafkaProducer;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaCommand implements DefaultCommand<SendMessage> {

    private final KafkaProducer kafkaProducer;

    @Override
    public SendMessage executeCommand(Context context) {
        log.info("Выполнение команды /kafka для chatId = {}", context.getUpdate().getMessage().getChatId());

        log.info("отправка сообщения в kafka");
        kafkaProducer.sendMessage(new Message());

        final SendMessage message = new SendMessage();
        message.setChatId(String.valueOf(context.getUpdate().getMessage().getChatId()));
        message.setText("сообщение было отправлено");
        return message;
    }
}
