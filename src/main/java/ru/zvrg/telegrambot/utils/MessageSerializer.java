package ru.zvrg.telegrambot.utils;

import com.google.gson.Gson;
import ru.zvrg.telegrambot.dto.kafka.impl.Message;

public class MessageSerializer {

    private static final Gson gson = new Gson();

    public static String serialize(Message message) {
        return gson.toJson(message);
    }

    public static Message deserialize(String message) {
        return gson.fromJson(message, Message.class);
    }
}
