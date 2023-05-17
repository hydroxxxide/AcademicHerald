package com.example.academicherald.controllers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.mappers.CommentMapper;
import com.example.academicherald.models.Comment;
import com.example.academicherald.services.CommentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {
    private final CommentService commentService;
    private CommentMapper commentMapper;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create/{userId}/{publicationId}")
    public CommentDto create(@RequestBody Comment comment, @PathVariable Long userId, @PathVariable Long publicationId){
        return commentMapper.convertToDto(commentService.create(comment, userId, publicationId));
    }
    @GetMapping("/getById/{id}")
    public CommentDto getById(@PathVariable Long id){
        return commentMapper.convertToDto(commentService.getCommentById(id));
    }
//    @GetMapping("/getAllComments")

}
