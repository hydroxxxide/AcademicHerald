package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.models.Publication;
import com.example.academicherald.services.PublicationService;
import org.modelmapper.ModelMapper;
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

//    @PostMapping("/create")
//    public PublicationDto create(@RequestBody Publication publication,
//                                 @RequestParam Long userId,
//                                 @RequestParam Long categoryId) {
//        return mapper.convertToDto(publicationService.createPublication(publication, userId, categoryId));
//    }
@PostMapping("/create")
public PublicationDto create(@RequestBody PublicationDto publicationDto,
                             @RequestParam Long userId,
                             @RequestParam Long categoryId,
                             @RequestParam Long[] tagIds) {
    ModelMapper modelMapper = new ModelMapper();

    Publication publication = modelMapper.map(publicationDto, Publication.class);
    Publication createdPublication = publicationService.createPublication(publication, userId, categoryId, tagIds);

    PublicationDto createdPublicationDto = modelMapper.map(createdPublication, PublicationDto.class);
    return createdPublicationDto;
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
//    @GetMapping("/listByTag/{tagId}")
//    public List<PublicationDto> listByTag(@PathVariable Long tagId) {
//        List<Publication> publications = publicationService.getPublicationsByTagId(tagId);
//        return mapper.convertToDTOList(publications);
//    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        publicationService.delete(id);
    }
}
