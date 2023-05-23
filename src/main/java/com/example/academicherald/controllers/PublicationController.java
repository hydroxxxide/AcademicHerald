package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.models.Category;
import com.example.academicherald.models.Publication;
import com.example.academicherald.services.CategoryService;
import com.example.academicherald.services.PublicationService;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;
    private final CategoryService categoryService;
    private final PublicationMapper mapper;
    private final ModelMapper modelMapper;
    ;

    public PublicationController(PublicationService publicationService, CategoryService categoryService, PublicationMapper mapper, ModelMapper modelMapper) {
        this.publicationService = publicationService;
        this.categoryService = categoryService;
        this.mapper = mapper;
        this.modelMapper = modelMapper;
    }

    @PostMapping("/create")
    public ResponseEntity<PublicationDto> createPublication(
            @RequestBody PublicationDto publicationDto,
            @RequestParam Long userId,
            @RequestParam List<Long> categoryIds,
            @RequestParam Long[] tagIds
    ) {
        Publication publication = modelMapper.map(publicationDto, Publication.class);
        List<Category> categories = categoryService.getCategoriesByIds(categoryIds);
        Publication createdPublication = publicationService.createPublication(publication, userId, categories, tagIds);
        PublicationDto createdPublicationDto = modelMapper.map(createdPublication, PublicationDto.class);
        return ResponseEntity.ok(createdPublicationDto);
    }

    @GetMapping("/get/{id}")
    public PublicationDto getById(@PathVariable Long id) {
        return mapper.convertToDto(publicationService.getById(id));
    }

    @GetMapping("/get/all")
    public List<PublicationDto> getAll() {
        return mapper.convertToDTOList(publicationService.getAll());
    }

    @PutMapping("/update")
    public PublicationDto update(@RequestBody Publication publication) {
        return mapper.convertToDto(publicationService.update(publication));
    }

    @GetMapping("/listByTag/{tagId}")
    public List<PublicationDto> listByTag(@PathVariable Long tagId) {
        List<Publication> publications = publicationService.getPublicationsByTagId(tagId);
        return mapper.convertToDTOList(publications);
    }

    @GetMapping("/getByCategory/{categoryId}")
    public List<PublicationDto> getPublicationsByCategoryId(@PathVariable Long categoryId) {
        List<Publication> publications = publicationService.getPublicationsByCategoryId(categoryId);
        // Преобразование сущностей Publication в DTO PublicationDto
        List<PublicationDto> publicationDtos = publications.stream()
                .map(publication -> modelMapper.map(publication, PublicationDto.class))
                .collect(Collectors.toList());
        return publicationDtos;
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        publicationService.delete(id);
    }

    @GetMapping("/user/{authorId}")
    public List<PublicationDto> getPublicationsByUser(@PathVariable Long authorId) {
        List<Publication> publications = publicationService.getPublicationsByAuthorId(authorId);
        return mapper.convertToDTOList(publications);
    }

}
