package ru.kpfu.itis.models.dtos;

import lombok.Data;

@Data
public class MessageDto {
    private String from;
    private String text;
}