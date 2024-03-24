package ru.zvrg.telegrambot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import ru.zvrg.telegrambot.dto.ValuteModel;

import java.io.IOException;
import java.net.URL;

@PropertySource("application.properties")
@Service
public class MoneyService {
    @Value("${valute.url}")
    private URL url;
    private JSONService jsonService;

    @Autowired
    public MoneyService(JSONService jsonService) {
        this.jsonService = jsonService;
    }

    public String getValuesFromCB(){
        try {
            return jsonService.readJSON(url);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
