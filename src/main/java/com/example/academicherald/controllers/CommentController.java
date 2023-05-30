package com.example.academicherald.controllers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.mappers.CommentMapper;
import com.example.academicherald.models.Comment;
import com.example.academicherald.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private CommentMapper commentMapper;

    public CommentController(CommentService commentService, CommentMapper commentMapper) {
        this.commentService = commentService;
        this.commentMapper = commentMapper;
    }

    @PostMapping("/create")
    public CommentDto create(@RequestBody CommentDto commentDto,
                             @RequestParam Long userId,
                             @RequestParam Long publicationId) {
        Comment comment = commentMapper.convertToEntity(commentDto);
        Comment createdComment = commentService.create(comment, userId, publicationId);
        return commentMapper.convertToDto(createdComment);
    }

    @GetMapping("/getById/{id}")
    public CommentDto getById(@PathVariable Long id) {
        return commentMapper.convertToDto(commentService.getCommentById(id));
    }

    @GetMapping("/get")
    public List<CommentDto> get(@RequestParam Long publicationId) {
        return commentMapper.convertToDTOList(commentService.allCommentsByIdPublic(publicationId));
    }

    @GetMapping("/getByUser")
    public List<CommentDto> getCommentByUser(@RequestParam Long userId) {
        return commentMapper.convertToDTOList(commentService.allCommentsByUser(userId));
    }

    @PutMapping("/update")
    public CommentDto update(@RequestBody Comment comment) {
        return commentMapper.convertToDto(commentService.updateComment(comment));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        commentService.deleteComment(id);
    }
}
