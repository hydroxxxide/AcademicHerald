package com.example.academicherald.controllers;

import com.example.academicherald.dto.UserDTO;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.models.User;
import com.example.academicherald.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public UserDTO create(@RequestBody User user){
        return mapper.convertToDTO(service.create(user));
    }

    @GetMapping("/get/{id}")
    public UserDTO getById(@PathVariable Long id){
        return mapper.convertToDTO(service.getById(id));
    }

    @PutMapping("/update")
    public UserDTO update(@RequestBody User u1){
        return mapper.convertToDTO(service.update(u1));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
