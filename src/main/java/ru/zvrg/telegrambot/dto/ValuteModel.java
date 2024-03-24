package ru.zvrg.telegrambot.dto;

import lombok.Data;

@Data
public class ValuteModel {
    String ID;
    String numCode;
    String charCode;
    Integer nominal;
    String name;
    Double value;
    Double previous;
}
