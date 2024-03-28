package ru.zvrg.telegrambot.utils.constants;

public class Constants {

    public static class Commands {
        public static final String START = "/start";
        public static final String START_DESCRIPTION = "Начало диалога с ботом";
        public static final String VALUTES = "/valutes";
        public static final String VALUTES_DESCRIPTION = "Вывести значение валют";
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
}
