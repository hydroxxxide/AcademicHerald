package com.example.academicherald.controllers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.entity.User;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.CommentMapper;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.services.CommentService;
import com.example.academicherald.services.PublicationService;
import com.example.academicherald.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
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
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public AdminController(PublicationService publicationService,
                           UserService userService,
                           PublicationMapper publicationMapper,
                           CommentService commentService,
                           CommentMapper commentMapper) {
        this.publicationService = publicationService;
        this.userService = userService;
        this.publicationMapper = publicationMapper;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    // управление публикациями
    @PutMapping("/confirm/{publicationId}")
    public ResponseMessage<?> confirmPublication(@PathVariable Long publicationId) {
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
    public ResponseMessage<String> rejectPublication(@PathVariable Long publicationId) {
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
    public List<PublicationDto> getAllRejectedPublications() {
        return publicationMapper.convertToDTOList(publicationService.getAllRejected());

    }


    //управление пользователями
    @GetMapping("/get/users")
    public ResponseEntity<byte[]> downloadUsersInfo() {
        List<User> users = userService.getAll();
        return userService.exportToExcel(users);
    }

    @DeleteMapping("/user/delete/{id}")
    public ResponseMessage<String> deleteUser(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    userService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: deleteUser", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    // получение всех комментариев по пользователю
    @GetMapping("/comment/user/{userId}")
    public ResponseMessage<List<CommentDto>> getCommentByUserId(@PathVariable Long userId) {
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDTOList(commentService.allCommentsByUser(userId)),
                    ResultCode.SUCCESS,
                    "Комментарии по пользователю успешно найдены",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("AdminController: getCommentByUserId", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
