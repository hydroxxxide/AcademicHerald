package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.models.Publication;
import com.example.academicherald.services.PublicationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/publication")
public class PublicationController {
    private final PublicationService service;
    private final PublicationMapper mapper;

    public PublicationController(PublicationService service, PublicationMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public PublicationDto create(Publication publication){
        return mapper.convertToDto(service.create(publication));
    }

    @GetMapping("/get/{id}")
    public PublicationDto getById(@PathVariable Long id){
        return mapper.convertToDto(service.getById(id));
    }

    @PutMapping("/update")
    public PublicationDto update(@RequestBody Publication publication){
        return mapper.convertToDto(service.update(publication));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
