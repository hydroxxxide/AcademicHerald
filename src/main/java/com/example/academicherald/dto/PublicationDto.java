package com.example.academicherald.dto;

import com.example.academicherald.enums.PublicationType;
import com.example.academicherald.models.Category;
import com.example.academicherald.models.User;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class PublicationDto {
    private String title;
    private String subtitle;
    private String text;
    private Timestamp dateOfCreation;
    private Category category;
    private User user;
    @Enumerated(EnumType.STRING)
    private PublicationType type;
}
