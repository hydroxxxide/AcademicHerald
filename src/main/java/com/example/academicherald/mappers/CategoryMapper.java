package com.example.academicherald.mappers;

import com.example.academicherald.dto.CategoryDto;
import com.example.academicherald.entity.Category;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapper {
    private final ModelMapper mapper;

    public CategoryMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<CategoryDto> convertToDTOList(List<Category> categories) {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        for (Category c : categories) {
            categoryDtoList.add(convertToDto(c));
        }
        return categoryDtoList;
    }
    public Category convertToEntity(CategoryDto categoryDto) {
        return mapper.map(categoryDto, Category.class);
    }
    public CategoryDto convertToDto(Category category) {
        return mapper.map(category, CategoryDto.class);
    }


}
