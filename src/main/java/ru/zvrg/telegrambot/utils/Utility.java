package ru.zvrg.telegrambot.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.time.LocalDate;

import static ru.zvrg.telegrambot.utils.constants.Constants.JsonAttributes.TIMESTAMP;

public class Utility {

    //TODO исправить метод так как дата не считывается (в текущий момент метод не используется)
    public static boolean compareDateFromJson(JsonObject jsonObject) {
        JsonObject data = jsonObject.getAsJsonObject(TIMESTAMP);
        var date = new Gson().toJson(data, LocalDate.class);
        var local = LocalDate.now();
        return local.equals(date);
    }
}
