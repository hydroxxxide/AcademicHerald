package com.example.academicherald.mappers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.models.Comment;
import com.example.academicherald.models.Publication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    private final ModelMapper mapper;

    public CommentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public CommentDto convertToDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
}
