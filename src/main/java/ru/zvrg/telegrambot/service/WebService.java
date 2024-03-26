package ru.zvrg.telegrambot.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Slf4j
@Service
@RequiredArgsConstructor
public class WebService {

    private final FileHandler fileHandler;
    private final Gson gson;

    public JsonObject getJsonFileFromUrl(String jsonUrl, String path) throws IOException {
        final URL url = new URL(jsonUrl);
        final StringBuilder jsonContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        } catch (Exception ignored) {
            log.info("Произошла ошибка {}", ignored.getMessage());
        }

        final JsonObject jsonObject = gson.fromJson(jsonContent.toString(), JsonObject.class);
        fileHandler.saveJsonToFile(jsonObject, path);

        return jsonObject;
    }
}
