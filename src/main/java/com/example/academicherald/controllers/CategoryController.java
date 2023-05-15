package com.example.academicherald.controllers;

import com.example.academicherald.dto.CategoryDto;
import com.example.academicherald.mappers.CategoryMapper;
import com.example.academicherald.models.Category;
import com.example.academicherald.services.CategoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService service;
    private final CategoryMapper mapper;

    public CategoryController(CategoryService service, CategoryMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public CategoryDto create(Category category){
        return mapper.convertToDto(service.create(category));
    }

    @GetMapping("/get/{id}")
    public CategoryDto getById(@PathVariable Long id){
        return mapper.convertToDto(service.getById(id));
    }

    @PutMapping("/update")
    public CategoryDto update(@RequestBody Category category){
        return mapper.convertToDto(service.update(category));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
