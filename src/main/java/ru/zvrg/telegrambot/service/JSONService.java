package ru.zvrg.telegrambot.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

@Service
public class JSONService {

    public String getJSONFile(String link) throws IOException {
        final URL url = new URL(link);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        StringBuilder jsonContent = new StringBuilder();

        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }

        reader.close();

        return jsonContent.toString();
    }
}
