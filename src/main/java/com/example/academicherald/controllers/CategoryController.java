package com.example.academicherald.controllers;

import com.example.academicherald.dto.CategoryDto;
import com.example.academicherald.mappers.CategoryMapper;
import com.example.academicherald.entity.Category;
import com.example.academicherald.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService categoryService, CategoryMapper mapper) {
        this.categoryService = categoryService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public CategoryDto create(@RequestBody CategoryDto categoryDto) {
        Category category = mapper.convertToEntity(categoryDto);
        Category createdCategory = categoryService.create(category);
        return mapper.convertToDto(createdCategory);
    }

    @GetMapping("/get/{id}")
    public CategoryDto getById(@PathVariable Long id){
        return mapper.convertToDto(categoryService.getById(id));
    }

    @GetMapping("/get/all")
    public List<CategoryDto> getAll() {
        return mapper.convertToDTOList(categoryService.getAll());
    }

    @PutMapping("/update")
    public CategoryDto update(@RequestBody CategoryDto categoryDto) {
        return mapper.convertToDto(categoryService.update(mapper.convertToEntity(categoryDto)));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        categoryService.delete(id);
    }
}
