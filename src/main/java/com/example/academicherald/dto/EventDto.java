package com.example.academicherald.dto;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
public class EventDto {
    private String title;

    private String subtitle;

    private String text;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation;

    private UserDto author;

    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfEvent;

    private Timestamp startTime;

    private String location;

    private Double duration;
}
