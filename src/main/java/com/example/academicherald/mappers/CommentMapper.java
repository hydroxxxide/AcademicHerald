package com.example.academicherald.mappers;

import com.example.academicherald.dto.CommentDTO;
import com.example.academicherald.models.Comment;
import org.modelmapper.ModelMapper;

public class CommentMapper {
    private final ModelMapper mapper;

    public CommentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public CommentDTO convertToDTO(Comment comment){
        return mapper.map(comment, CommentDTO.class);
    }

}
