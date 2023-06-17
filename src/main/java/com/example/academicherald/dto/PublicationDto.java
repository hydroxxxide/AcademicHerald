package com.example.academicherald.dto;

import com.example.academicherald.enums.PublicationType;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class PublicationDto {
    private Long id;
    private String title;
    private String subtitle;
    private String text;
    private Boolean pass = false;
    @DateTimeFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime dateOfCreation;
    private CategoryDto category;
    private List<TagDto> tags;
    private UserDto author;
    @Enumerated(EnumType.STRING)
    private PublicationType type;
    private String preview; // ссылка к картинке
}
