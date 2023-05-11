package com.example.academicherald.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class PublicationsDTO {
    private String title;
    private LocalDate dateOfCreation;
}
