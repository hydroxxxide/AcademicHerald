package com.example.academicherald.controllers;

import com.example.academicherald.models.Publication;
import com.example.academicherald.services.PublicationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final PublicationService publicationService;

    public AdminController(PublicationService publicationService) {
        this.publicationService = publicationService;
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
    public List<Publication> showRejectedPublications(){
        return publicationService.getAllRejected();
    }

}
