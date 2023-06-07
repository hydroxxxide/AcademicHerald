package com.example.academicherald.controllers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.dto.UserDto;
import com.example.academicherald.entity.Comment;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.entity.User;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.CommentMapper;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.services.CommentService;
import com.example.academicherald.services.PublicationService;
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
    private final PublicationService publicationService;
    private final PublicationMapper publicationMapper;
    private final CommentService commentService;
    private final CommentMapper commentMapper;

    public UserController(UserService userService,
                          UserMapper mapper,
                          PublicationService publicationService,
                          PublicationMapper publicationMapper, CommentService commentService, CommentMapper commentMapper) {
        this.userService = userService;
        this.mapper = mapper;
        this.publicationService = publicationService;
        this.publicationMapper = publicationMapper;
        this.commentService = commentService;
        this.commentMapper = commentMapper;
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

    @GetMapping("/get/all")
    public List<UserDto> getAll() {
        return mapper.convertToDTOList(userService.getAll());
    }

    @PutMapping("/update")
    public ResponseMessage<UserDto> update(@RequestBody User newUser) {
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

    // управление публикациями
    @PostMapping("/publication/like/{pId}/{uId}")
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

    @PostMapping("/publication/create")
    public ResponseMessage<PublicationDto> createPublication(
            @RequestBody PublicationDto publicationDto,
            @RequestParam Long userId,
            @RequestParam Long categoryId,
            @RequestParam Long[] tagsIds
    ) {
        Publication publication = publicationMapper.convertToEntity(publicationDto);
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDto(publicationService.create(publication, userId, categoryId, tagsIds)),
                    ResultCode.SUCCESS,
                    "Публикация успешно создана",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: createPublication", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/publication/get/{id}")
    public ResponseMessage<PublicationDto> getPublicationById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDto(publicationService.getById(id)),
                    ResultCode.SUCCESS,
                    "Публикация успешно найдена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: getPublicationById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/publication/get/all")
    public ResponseMessage<List<PublicationDto>> getAllPublications() {
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDTOList(publicationService.getAllAccepted()),
                    ResultCode.SUCCESS,
                    "Список публикаций",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: getAllPublications", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/publication/update")
    public ResponseMessage<PublicationDto> updatePublication(@RequestBody Publication publication,
                                                             @RequestParam Long userId,
                                                             @RequestParam Long categoryId) {
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDto(publicationService.update(publication, userId, categoryId)),
                    ResultCode.SUCCESS,
                    "Публикация успешно обновлена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: updatePublication", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/publication/delete")
    public ResponseMessage<String> deletePublication(@RequestParam Long publicationId, Long userId) {
        try {
            return new ResponseMessage<>(
                    publicationService.delete(publicationId, userId),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: deletePublication", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    //Вытаскиваем список публикаций по id тега
    @GetMapping("/publication/tag/{tagId}")
    public ResponseMessage<List<PublicationDto>> listOfPublicationsByTag(@PathVariable Long tagId) {
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDTOList(publicationService.getPublicationsByTagId(tagId)),
                    ResultCode.SUCCESS,
                    "Список публикаций по тегу успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: listOfPublicationsByTag", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    //Вытаскиваем список публикаций по id категории
    @GetMapping("/publication/category/{categoryId}")
    public ResponseMessage<List<PublicationDto>> getPublicationsByCategoryId(@PathVariable Long categoryId) {
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDTOList(publicationService.getPublicationsByCategoryId(categoryId)),
                    ResultCode.SUCCESS,
                    "Список публикаций по категории успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: getPublicationsByCategoryId", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    //Вытаскиваем список публикаций по id автора(какие посты он выложил)
    @GetMapping("/publication/user/{authorId}")
    public ResponseMessage<List<PublicationDto>> getPublicationsByUser(@PathVariable Long authorId) {
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDTOList(publicationService.getPublicationsByAuthorId(authorId)),
                    ResultCode.SUCCESS,
                    "Список публикаций по автору успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: getPublicationsByUser", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/publication/popular")
    public ResponseMessage<List<PublicationDto>> getPopularPublicationList() {
        try {
            return new ResponseMessage<>(
                    publicationMapper.convertToDTOList(publicationService.sortByPopularity()),
                    ResultCode.SUCCESS,
                    "Список публикаций по популярности успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: getPopular", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/comment/create")
    public ResponseMessage<CommentDto> createComment(@RequestBody CommentDto commentDto,
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
            log.error("UserController: createComment", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/comment/getById/{id}")
    public ResponseMessage<CommentDto> getCommentById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDto(commentService.getCommentById(id)),
                    ResultCode.SUCCESS,
                    "Комментарий успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: getCommentById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/comment/update")
    public ResponseMessage<CommentDto> updateComment(@RequestBody Comment comment) {
        try {
            return new ResponseMessage<>(
                    commentMapper.convertToDto(commentService.updateComment(comment)),
                    ResultCode.SUCCESS,
                    "Комментарий успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: updateComment", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/comment/delete")
    public ResponseMessage<String> deleteComment(@RequestParam Long commentId, @RequestParam Long userId) {
        try {
            return new ResponseMessage<>(
                    commentService.deleteComment(commentId, userId),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("UserController: deleteComment", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
