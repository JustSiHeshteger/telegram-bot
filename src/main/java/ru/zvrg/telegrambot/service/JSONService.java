package ru.zvrg.telegrambot.service;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Pattern;


@Service
public class JSONService {
    public String readJSON (URL url) throws IOException {
        Scanner scanner = new Scanner((InputStream) url.getContent());
        String result = "";
        while (scanner.hasNext()){
            scanner.skip(Pattern.compile("\"PreviousDate\": "));
            scanner.skip(Pattern.compile("\"PreviousURL\": "));
            scanner.skip(Pattern.compile("\"Timestamp\": "));
            result += scanner.nextLine();
        }

        return result;
    }


}
