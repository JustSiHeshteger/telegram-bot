package ru.zvrg.telegrambot.service;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.dto.Valute;

import java.io.IOException;
import java.util.*;

import static ru.zvrg.telegrambot.utils.Utility.compareDateFromJson;
import static ru.zvrg.telegrambot.utils.constants.Constants.JsonAttributes.VALUTE;
import static ru.zvrg.telegrambot.utils.constants.Constants.Paths.VALUTES_PATH;
import static ru.zvrg.telegrambot.utils.constants.Constants.Urls.VALUTES_URL;

@Service
@RequiredArgsConstructor
public class ValuteService {

    private final WebService webService;
    private final FileHandler fileHandler;
    private final Gson gson;

    public List<Valute> getValuteFromCbr() throws IOException {
        JsonObject jsonObject = fileHandler.readJsonFromFile(VALUTES_PATH);

        if (Objects.equals(jsonObject, null) || compareDateFromJson(jsonObject)) {
            jsonObject = gson.fromJson(webService.getJsonFileFromUrl(VALUTES_URL, VALUTES_PATH), JsonObject.class);
        }

        final JsonObject valuteObject = jsonObject.getAsJsonObject(VALUTE);
        final List<Valute> valuteList = new ArrayList<>();

        for (Map.Entry<String, JsonElement> entry : valuteObject.entrySet()) {
            final JsonObject valuteJson = entry.getValue().getAsJsonObject();
            final Valute valute = gson.fromJson(valuteJson, Valute.class);
            valuteList.add(valute);
        }

        return valuteList;
    }
}
