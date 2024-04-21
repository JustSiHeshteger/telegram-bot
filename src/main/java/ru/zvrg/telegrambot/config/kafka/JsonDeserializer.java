package ru.zvrg.telegrambot.config.kafka;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;
import java.util.Objects;

import static ru.zvrg.telegrambot.utils.constants.Constants.EncodingConstants.GSON;
import static ru.zvrg.telegrambot.utils.constants.Constants.GsonConstants.TYPE_TOKEN;
import static ru.zvrg.telegrambot.utils.constants.Constants.EncodingConstants.ENCODING_UTF_8;

public class JsonDeserializer<T> implements Deserializer<T> {
    private TypeToken<T> typeToken;
    private Gson gson;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Deserializer.super.configure(configs, isKey);

        gson = (Gson) configs.get(GSON);
        if (Objects.isNull(gson)) {
            throw new IllegalArgumentException("GSON was not set");
        }

        typeToken = (TypeToken<T>) configs.get(TYPE_TOKEN);
        if (Objects.isNull(typeToken)) {
            throw new IllegalArgumentException("TYPE TOKEN was not set");
        }
    }

    @Override
    public T deserialize(String s, byte[] bytes) {
        try {
            if (Objects.isNull(bytes)) {
                return null;
            }
            var valueAsString = new String(bytes, ENCODING_UTF_8);
            return gson.fromJson(valueAsString, typeToken);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to object", e);
        }
    }

    @Override
    public void close() {
        Deserializer.super.close();
    }
}

