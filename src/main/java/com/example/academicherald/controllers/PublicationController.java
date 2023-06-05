package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.mappers.PublicationMapper;
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


    @PostMapping("/create")
    public PublicationDto createPublication(
            @RequestBody PublicationDto publicationDto,
            @RequestParam Long userId,
            @RequestParam Long categoryId,
            @RequestParam Long[] tagsIds
    ) {
        Publication publication = mapper.convertToEntity(publicationDto);
        return mapper.convertToDto(publicationService.create(publication, userId, categoryId, tagsIds));
    }

    @GetMapping("/get/{id}")
    public PublicationDto getById(@PathVariable Long id) {
        return mapper.convertToDto(publicationService.getById(id));
    }

    @GetMapping("/get/all")
    public List<PublicationDto> getAll() {
        return mapper.convertToDTOList(publicationService.getAllAccepted());
    }

    @PutMapping("/update")
    public PublicationDto update(@RequestBody Publication publication,
                                 @RequestParam Long userId,
                                 @RequestParam Long categoryId) throws Exception{
        return mapper.convertToDto(publicationService.update(publication, userId, categoryId));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        publicationService.delete(id);
    }
    //Вытаскиваем список публикаций по id тега
    @GetMapping("/listByTag/{tagId}")
    public List<PublicationDto> listByTag(@PathVariable Long tagId) {
        List<Publication> publications = publicationService.getPublicationsByTagId(tagId);
        return mapper.convertToDTOList(publications);
    }
    //Вытаскиваем список публикаций по id категории
    @GetMapping("/getByCategory/{categoryId}")
    public List<PublicationDto> getPublicationsByCategoryId(@PathVariable Long categoryId) {
        List<Publication> publications = publicationService.getPublicationsByCategoryId(categoryId);
        return mapper.convertToDTOList(publications);
    }
    //Вытаскиваем список публикаций по id автора(какие посты он выложил)
    @GetMapping("/user/{authorId}")
    public List<PublicationDto> getPublicationsByUser(@PathVariable Long authorId) {
        List<Publication> publications = publicationService.getPublicationsByAuthorId(authorId);
        return mapper.convertToDTOList(publications);
    }

    @GetMapping("/popular")
    public List<PublicationDto> getPopularPublicationList() {
        return mapper.convertToDTOList(publicationService.sortByPopularity());
    }
}
