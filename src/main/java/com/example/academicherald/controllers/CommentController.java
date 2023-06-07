package com.example.academicherald.controllers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.entity.Comment;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.CommentMapper;
import com.example.academicherald.services.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Slf4j
public class CommentController {
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/create")
    public ResponseMessage<CommentDto> create(@RequestBody CommentDto commentDto,
                                              @RequestParam Long userId,
                                              @RequestParam Long publicationId) {
        Comment comment = commentMapper.convertToEntity(commentDto);
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDto(commentService.create(comment, userId, publicationId)),
                            ResultCode.SUCCESS,
                            "Комментарий успешно создан",
                            ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CommentController: create", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseMessage<CommentDto> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDto(commentService.getCommentById(id)),
                    ResultCode.SUCCESS,
                    "Комментарий успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CommentController: getById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/getByPublication/{publicationId}")
    public ResponseMessage<List<CommentDto>> getByPublicationId(@PathVariable Long publicationId) {
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDTOList(commentService.allCommentsByIdPublic(publicationId)),
                    ResultCode.SUCCESS,
                    "Комментарии по публикации успешно найдены",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CommentController: getByPublicationId", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/getByUser/{userId}")
    public ResponseMessage<List<CommentDto>> getCommentByUserId(@PathVariable Long userId) {
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDTOList(commentService.allCommentsByUser(userId)),
                    ResultCode.SUCCESS,
                    "Комментарии по пользователю успешно найдены",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CommentController: getCommentByUserId", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/update")
    public ResponseMessage<CommentDto> update(@RequestBody Comment comment) {
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDto(commentService.updateComment(comment)),
                    ResultCode.SUCCESS,
                    "Комментарий успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CommentController: update", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<String> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    commentService.deleteComment(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("CommentController: delete", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
