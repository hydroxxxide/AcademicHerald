package com.example.academicherald.controllers;

import com.example.academicherald.dto.CategoryDto;
import com.example.academicherald.entity.Category;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.CategoryMapper;
import com.example.academicherald.services.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Slf4j

public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping("/create")
    public ResponseMessage<CategoryDto> create(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.convertToEntity(categoryDto);
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDto(categoryService.create(category)),
                    ResultCode.SUCCESS,
                    "Категория успешно создана",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: createCategory", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseMessage<CategoryDto> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDto(categoryService.getById(id)),
                    ResultCode.SUCCESS,
                    "Категория успешно найдена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: getCategoryById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/get/all")
    public ResponseMessage<List<CategoryDto>> getAll() {
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDTOList(categoryService.getAll()),
                    ResultCode.SUCCESS,
                    "Список всех категорий",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: getAllCategories", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/update")
    public ResponseMessage<CategoryDto> update(@RequestBody Category category) {
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDto(categoryService.update(category)),
                    ResultCode.SUCCESS,
                    "Категория успешно обновлена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: update", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<String> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    categoryService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CategoryController: delete", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
