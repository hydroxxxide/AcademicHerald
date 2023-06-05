package com.example.academicherald.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
public class EventDto {
    private String title; // заголовок ивента

    private String subtitle; // подзаголовок

    private String text; // текст, описание

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation; // дата создания, публикации

    private UserDto author; // автор публикации

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfEvent; // дата проведения ивента

    private Timestamp startTime; // начало ивента

    private String location; // место проведения ивента

    private Double duration; // продолжительность ивента
}
