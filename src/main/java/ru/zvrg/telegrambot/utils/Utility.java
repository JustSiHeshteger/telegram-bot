package ru.zvrg.telegrambot.utils;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import static ru.zvrg.telegrambot.utils.constants.Constants.JsonAttributes.TIMESTAMP;

@Slf4j
public class Utility {

    public static boolean compareDateFromJson(JsonObject jsonObject) {
        final JsonPrimitive jsonDate = jsonObject.getAsJsonPrimitive(TIMESTAMP);
        final OffsetDateTime dateTimeFromJson = OffsetDateTime.parse(jsonDate.getAsString());
        log.info("Дата совпадает с сегодняшней: {}", LocalDate.now().equals(dateTimeFromJson.toLocalDate()));
        return !LocalDate.now().equals(dateTimeFromJson.toLocalDate());
    }

}
