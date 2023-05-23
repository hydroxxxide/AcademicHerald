package com.example.academicherald.controllers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.models.User;
import com.example.academicherald.services.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private final UserMapper mapper;

    public UserController(UserService userService, UserMapper mapper) {
        this.userService = userService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    public UserDto create(@RequestBody User user) {
        return mapper.convertToDTO(userService.create(user));
    }

    @GetMapping("/get/{id}")
    public UserDto getById(@PathVariable Long id) {
        return mapper.convertToDTO(userService.getById(id));
    }

    @GetMapping("/get/all")
    public List<UserDto> getAll() {
        return mapper.convertToDTOList(userService.getAll());
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody User newUser) {
        return mapper.convertToDTO(userService.update(newUser));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/reset")
    public boolean resetPassword(@RequestParam String email){
        return userService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    public boolean saveNewPassword(@PathVariable String resetToken, @RequestParam String password) {
        return userService.saveNewPassword(resetToken, password);
    }

    @PostMapping("/register")
    public UserDto register(@RequestBody UserDto userDto) {
        return mapper.convertToDTO(userService.create(mapper.convertToEntity(userDto)));
    }

}
