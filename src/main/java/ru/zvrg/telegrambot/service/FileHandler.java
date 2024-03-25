package ru.zvrg.telegrambot.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.zvrg.telegrambot.utils.constants.Constants.Paths.VALUTES_PATH;

@Slf4j
@Component
@RequiredArgsConstructor
public final class FileHandler {

    private final Gson gson;

    //FIXME изменить, чтобы в метод приходил путь из сервиса, так как метод можно переиспользовать
    public void saveJsonToFile(JsonObject json) {
        try (FileWriter writer = new FileWriter(VALUTES_PATH)) {
            openOrCreateDirectory();
            writer.write(new Gson().toJson(json));
            log.debug("Json сохранен в файл по пути {}", VALUTES_PATH);
        } catch (IOException e) {
            log.info("Не удалось сохранить файл. Ошибка = {}", e.getMessage());
        }
    }

    //FIXME изменить, чтобы в метод приходил путь из сервиса, так как метод можно переиспользовать
    public JsonObject readJsonFromFile() {
        JsonObject jsonObject = null;

        if (!Files.exists(Path.of(VALUTES_PATH))) {
            return jsonObject;
        }

        try (FileReader reader = new FileReader(VALUTES_PATH)) {
            jsonObject = gson.fromJson(reader, JsonObject.class);
        } catch (IOException | NullPointerException e) {
            log.info("Не удалось прочитать файл. Ошибка = {}", e.getMessage());
        }

        //TODO перенести метод на уровень выше
        /*if (!compareDateFromJson(jsonObject)) {
            return null;
        }*/

        return jsonObject;
    }

    private void openOrCreateDirectory() {
        try {
            Path resourcesPath = Paths.get("src", "main", "resources");
            if (!Files.exists(resourcesPath)) {
                Files.createDirectories(resourcesPath);
            }
        } catch (IOException e) {
            log.info("Не удалось открыть/создать директорию. Ошибка = {}", e.getMessage());
        }
    }
}
