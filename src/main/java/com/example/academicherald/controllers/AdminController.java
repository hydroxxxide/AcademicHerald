package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.services.PublicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class AdminController {
    private final PublicationService publicationService;
    private final PublicationMapper mapper;

    public AdminController(PublicationService publicationService, PublicationMapper mapper) {
        this.publicationService = publicationService;
        this.mapper = mapper;
    }

    @PutMapping("/confirm/{publicationId}")
    public void confirmPublication(@PathVariable Long publicationId){
        publicationService.confirmPublication(publicationId, true);
    }

    @PutMapping("/reject/{publicationId}")
    public void rejectPublication(@PathVariable Long publicationId){
        publicationService.confirmPublication(publicationId, false);
    }

    @GetMapping("/rejected/getAll")
    public List<PublicationDto> getAllRejectedPublications(){
        return mapper.convertToDTOList(publicationService.getAllRejected());
    }

}
