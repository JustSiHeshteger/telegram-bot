package ru.zvrg.telegrambot.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.dto.Valute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@PropertySource("application.properties")
public class ValuteService {

    @Value("${valute.url}")
    private String valuteUrl;

    private final JSONService jsonService;

    public ValuteService(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    public List<Valute> getValuteFromCbr() throws IOException {
        final Gson gson = new Gson();
        final JsonObject jsonObject = gson.fromJson(jsonService.getJSONFile(valuteUrl), JsonObject.class);
        final JsonObject valuteObject = jsonObject.getAsJsonObject("Valute");

        final List<Valute> valuteList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : valuteObject.entrySet()) {
            final JsonObject valuteJson = entry.getValue().getAsJsonObject();
            final Valute valute = gson.fromJson(valuteJson, Valute.class);
            valuteList.add(valute);
        }

        return valuteList;
    }
}
