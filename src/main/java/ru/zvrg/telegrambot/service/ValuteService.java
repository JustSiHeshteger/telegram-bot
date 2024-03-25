package ru.zvrg.telegrambot.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.dto.Valute;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static ru.zvrg.telegrambot.utils.constants.Constants.JsonAttributes.VALUTE;

@Service
@RequiredArgsConstructor
@PropertySource("application.properties")
public class ValuteService {

    @Value("${valute.url}")
    private String valuteUrl;

    private final WebService webService;
    private final FileHandler fileHandler;
    private final Gson gson;

    public List<Valute> getValuteFromCbr() throws IOException {
        JsonObject jsonObject = Optional.ofNullable(fileHandler.readJsonFromFile()).orElse(
                gson.fromJson(webService.getJsonFileFromCbr(valuteUrl), JsonObject.class)
        );
        JsonObject valuteObject = jsonObject.getAsJsonObject(VALUTE);

        List<Valute> valuteList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : valuteObject.entrySet()) {
            JsonObject valuteJson = entry.getValue().getAsJsonObject();

            Valute valute = gson.fromJson(valuteJson, Valute.class);
            valuteList.add(valute);
        }

        return valuteList;
    }
}
