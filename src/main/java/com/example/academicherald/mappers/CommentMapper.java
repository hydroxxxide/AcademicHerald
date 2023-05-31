package com.example.academicherald.mappers;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.models.Comment;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CommentMapper {
    private final ModelMapper mapper;

    public CommentMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public CommentDto convertToDto(Comment comment) {
        return mapper.map(comment, CommentDto.class);
    }
    public Comment convertToEntity(CommentDto commentDto){
        return mapper.map(commentDto, Comment.class);
    }
    public List<CommentDto> convertToDTOList(List<Comment> comments) {
        List<CommentDto> commentDtoList = new ArrayList<>();
        for (Comment comment : comments) {
            commentDtoList.add(convertToDto(comment));
        }
        return commentDtoList;
    }
}
