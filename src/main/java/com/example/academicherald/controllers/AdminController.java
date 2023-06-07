package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.dto.UserDto;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.services.PublicationService;
import com.example.academicherald.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Slf4j
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
    public ResponseMessage<?> confirmPublication(@PathVariable Long publicationId){
        try {
            return new ResponseMessage<>(
                    publicationService.confirmAndRejectPublication(publicationId, true),
                    ResultCode.SUCCESS,
                    "Публикация принята",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: confirmPublication", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/reject/{publicationId}")
    public ResponseMessage<String> rejectPublication(@PathVariable Long publicationId){
        try {
            return new ResponseMessage<>(
                    publicationService.confirmAndRejectPublication(publicationId, false),
                    ResultCode.SUCCESS,
                    "Публикация отклонена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: rejectPublication", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
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
