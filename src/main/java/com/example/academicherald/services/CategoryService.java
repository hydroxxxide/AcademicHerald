package com.example.academicherald.services;

import com.example.academicherald.entity.Category;
import com.example.academicherald.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository repository;

    public CategoryService(CategoryRepository repository) {
        this.repository = repository;
    }

    public Category create(Category category) {
        return repository.save(category);
    }

    public Category getById(Long id) {
        return repository.findByIdAndRdtIsNull(id);
    }

    public List<Category> getAll() {
        return repository.findAllByRdtIsNull();
    }

    public Category update(Category newCategory) {
        Category oldCategory = (getById(newCategory.getId()));
        oldCategory.setName(newCategory.getName());
        oldCategory.setPublications(newCategory.getPublications());
        return repository.save(oldCategory);
    }
    public void delete(Long id) {
        Category category = getById(id);
        category.setRdt(LocalDateTime.now());
        repository.save(category);
    }
    public List<Category> getCategoriesByIds(List<Long> categoryIds) {
        return repository.findAllById(categoryIds);
    }

}
