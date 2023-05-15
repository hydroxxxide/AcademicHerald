package com.example.academicherald.dto;

import com.example.academicherald.models.Publication;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDto {
    private String name;
    private List<Publication> publications;
}
