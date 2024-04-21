package ru.zvrg.telegrambot.config.kafka;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Map;
import java.util.Objects;

import static ru.zvrg.telegrambot.utils.constants.Constants.EncodingConstants.ENCODING_UTF_8;
import static ru.zvrg.telegrambot.utils.constants.Constants.EncodingConstants.GSON;

@Slf4j
public class JsonSerializer<T> implements Serializer<T> {

    private Gson gson;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
        gson = (Gson) configs.get(GSON);
        if (Objects.isNull(gson)) {
            throw new IllegalArgumentException("GSON was not set");
        }
    }

    @Override
    public byte[] serialize(String s, T data) {
        try {
            if (Objects.isNull(data)) {
                return null;
            }
            return gson.toJson(data).getBytes(ENCODING_UTF_8);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new SerializationException(e.getMessage());
        }
    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
