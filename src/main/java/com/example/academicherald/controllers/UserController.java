package com.example.academicherald.controllers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.entity.User;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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

    @GetMapping("/get/{id}")
    public ResponseMessage<UserDto> getById(@PathVariable Long id) throws Exception {
        try {
            return new ResponseMessage<>(
                    mapper.convertToDTO(userService.getById(id)),
                    ResultCode.SUCCESS,
                    "Пользователь успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: getById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/update")
    public ResponseMessage<UserDto> update(@RequestBody User newUser){
        try {
            return new ResponseMessage<>(
                    mapper.convertToDTO(userService.update(newUser)),
                    ResultCode.SUCCESS,
                    "Пользователь успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: update", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/payment")
    public String redirectToPaymentForm(){
        return "https://docs.google.com/forms/d/e/1FAIpQLSedV3V8kaTlySCfodun9n-_QwzvMkWV8GYKmIRos7sZN6wawA/viewform";
    }

    @PostMapping("/like/{pId}/{uId}")
    public ResponseMessage<String> likePublication(@PathVariable Long pId, @PathVariable Long uId) {
        try {
            return new ResponseMessage<>(
                    userService.likePublication(pId, uId),
                    ResultCode.SUCCESS,
                    "Пользователь успешно поставил лайк)",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: likePublication", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/get/users")
    public ResponseEntity<byte[]> downloadUsersInfo() throws IOException {
        List<User> users = userService.getAll();
        return userService.exportToExcel(users);
    }
}
