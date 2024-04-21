package ru.zvrg.telegrambot.utils.constants;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class Constants {

    public static class Commands {
        public static final String START = "/start";
        public static final String START_DESCRIPTION = "Начало диалога с ботом";
        public static final String VALUTES = "/valutes";
        public static final String VALUTES_DESCRIPTION = "Вывести значение валют";
        public static final String KAFKA = "/kafka";
        public static final String KAFKA_DESCRIPTION = "Тестовая команда для проверки kafka-cluster";
    }

    public static class Urls {
        public static final String VALUTES_URL = "https://www.cbr-xml-daily.ru/daily_json.js";
    }

    public static class Paths {
        public static final String VALUTES_PATH = "src/main/resources/json/actual_valutes.json";
    }

    public static class JsonAttributes {
        public static final String TIMESTAMP = "Timestamp";
        public static final String VALUTE = "Valute";
    }

    public static class DefaultValutes {
        public static final List<String> MOST_POPULAR_VALUTES = List.of("USD", "EUR");
    }

    public static class KafkaSettings {
        public static final String REQUEST_TOPIC_NAME = "request-topic";
        public static final String RESPONSE_TOPIC_NAME = "response-topic";
        public static final String BOOTSTRAP_SERVER = "localhost:9092";
        public static final String GROUP_ID = "my-group";
    }

    public static class GsonConstants {
        public static final String TYPE_TOKEN = "typeToken";
    }

    public static class EncodingConstants {
        public static final String ENCODING_UTF_8 = StandardCharsets.UTF_8.name();
        public static final String GSON = "gson";
    }
}
