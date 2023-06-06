package com.example.academicherald.controllers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.entity.User;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user")
@CrossOrigin(origins = "http://127.0.0.1:5500")

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
    public UserDto getById(@PathVariable Long id) throws Exception {
        return mapper.convertToDTO(userService.getById(id));
    }

    @PutMapping("/update")
    public UserDto update(@RequestBody User newUser){
        return mapper.convertToDTO(userService.update(newUser));
    }

    @GetMapping("/payment")
    public String redirectToPaymentForm(){
        return "https://docs.google.com/forms/d/e/1FAIpQLSedV3V8kaTlySCfodun9n-_QwzvMkWV8GYKmIRos7sZN6wawA/viewform";
    }

    @PostMapping("/like/{pId}/{uId}")
    public void likePublication(@PathVariable Long pId, @PathVariable Long uId) throws Exception {
        userService.likePublication(pId, uId);
    }
}
