package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.models.Publication;
import com.example.academicherald.services.PublicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService publicationService;
    private final PublicationMapper mapper;

    public PublicationController(PublicationService publicationService, PublicationMapper mapper) {
        this.publicationService = publicationService;
        this.mapper = mapper;
    }

    @PostMapping("/create/{user_id}")
    public PublicationDto create(@RequestBody Publication publication, @PathVariable Long user_id) {
        return mapper.convertToDto(publicationService.create(publication, user_id));
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

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        publicationService.delete(id);
    }
}
