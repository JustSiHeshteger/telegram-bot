package ru.zvrg.telegrambot.common;

import lombok.Data;

@Data
public class Valute {
    String ID;
    String NumCode;
    String CharCode;
    Integer Nominal;
    String Name;
    Double Value;
    Double Previous;
}
