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

@Slf4j
@Component
@RequiredArgsConstructor
public final class FileHandler {

    private final Gson gson;

    public void saveJsonToFile(JsonObject json, String path) {
        try (FileWriter writer = new FileWriter(path)) {
            openOrCreateDirectory();
            writer.write(new Gson().toJson(json));
            log.debug("Json сохранен в файл по пути {}", path);
        } catch (IOException e) {
            log.info("Не удалось сохранить файл. Ошибка = {}", e.getMessage());
        }
    }

    public JsonObject readJsonFromFile(String path) {
        JsonObject jsonObject = null;

        try (FileReader reader = new FileReader(path)) {
            jsonObject = gson.fromJson(reader, JsonObject.class);
        } catch (IOException | NullPointerException e) {
            log.info("Не удалось прочитать файл. Ошибка = {}", e.getMessage());
        }

        return jsonObject;
    }

    private void openOrCreateDirectory() {
        try {
            final Path resourcesPath = Paths.get("src", "main", "resources");
            if (!Files.exists(resourcesPath)) {
                Files.createDirectories(resourcesPath);
            }
        } catch (IOException e) {
            log.info("Не удалось открыть/создать директорию. Ошибка = {}", e.getMessage());
        }
    }
}
