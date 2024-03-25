package ru.zvrg.telegrambot.service;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
@Service
public class JSONService {

    public String getJSONFile(String link) throws IOException {
        StringBuilder jsonContent;
        final URL url = new URL(link);
        BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
        jsonContent = readFromFile(reader);
        return jsonContent.toString();
    }

    public StringBuilder readFromFile(BufferedReader reader) throws IOException {
        StringBuilder jsonContent = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonContent.append(line);
        }
        reader.close();
        return jsonContent;
    }
}
