package com.example.academicherald.mappers;

import com.example.academicherald.dto.CategoryDto;
import com.example.academicherald.models.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {
    private final ModelMapper mapper;

    public CategoryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CategoryDto convertToDto(Category category){
        return mapper.map(category, CategoryDto.class);
    }
}
