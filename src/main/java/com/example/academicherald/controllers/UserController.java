package com.example.academicherald.controllers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.entity.User;
import com.example.academicherald.services.UserService;
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
    @ResponseBody
    public UserDto create(@RequestBody User user) {
        return mapper.convertToDTO(userService.create(user));
    }

    @GetMapping("/get/{id}")
    @ResponseBody
    public UserDto getById(@PathVariable Long id) {
        return mapper.convertToDTO(userService.getById(id));
    }

    @GetMapping("/get/all")
    @ResponseBody
    public List<UserDto> getAll() {
        return mapper.convertToDTOList(userService.getAll());
    }

    @PutMapping("/update")
    @ResponseBody
    public UserDto update(@RequestBody User newUser) {
        return mapper.convertToDTO(userService.update(newUser));
    }

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public void delete(@PathVariable Long id) {
        userService.delete(id);
    }

    @GetMapping("/reset")
    @ResponseBody
    public boolean resetPassword(@RequestParam String email){
        return userService.resetPassword(email);
    }

    @PostMapping("/reset/{resetToken}")
    @ResponseBody
    public boolean saveNewPassword(@PathVariable String resetToken, @RequestParam String password) {
        return userService.saveNewPassword(resetToken, password);
    }
    @GetMapping("/payment")
    public String redirectToPaymentForm(){
        return "redirect:https://docs.google.com/forms/d/e/1FAIpQLSedV3V8kaTlySCfodun9n-_QwzvMkWV8GYKmIRos7sZN6wawA/viewform";
    }


}
