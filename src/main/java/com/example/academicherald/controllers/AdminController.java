package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.dto.UserDto;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.services.PublicationService;
import com.example.academicherald.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class AdminController {
    private final PublicationService publicationService;
    private final UserService userService;
    private final PublicationMapper publicationMapper;
    private final UserMapper userMapper;

    public AdminController(PublicationService publicationService, UserService userService, PublicationMapper publicationMapper, UserMapper userMapper) {
        this.publicationService = publicationService;
        this.userService = userService;
        this.publicationMapper = publicationMapper;
        this.userMapper = userMapper;
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
        return publicationMapper.convertToDTOList(publicationService.getAllRejected());
    }

    @GetMapping("/get/all")
    public List<UserDto> getAll() {
        return userMapper.convertToDTOList(userService.getAll());
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}
