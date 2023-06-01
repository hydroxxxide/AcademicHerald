package com.example.academicherald.dto;

import com.example.academicherald.enums.PublicationType;
import com.example.academicherald.models.Category;
import com.example.academicherald.models.Tag;
import com.example.academicherald.models.User;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Data
public class PublicationDto {
    private String title;
    private String subtitle;
    private String text;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation;
    private CategoryDto category;
    private List<TagDto> tags;
    private UserDto author;
    @Enumerated(EnumType.STRING)
    private PublicationType type;
}
