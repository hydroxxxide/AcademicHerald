package com.example.academicherald.dto;

import com.example.academicherald.enums.PublicationType;
import com.example.academicherald.models.Category;
import com.example.academicherald.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class PublicationDto {
    private String title;
    private String subtitle;
    private String text;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation;
    private Category category;
    private User author;
    @Enumerated(EnumType.STRING)
    private PublicationType type;
}
