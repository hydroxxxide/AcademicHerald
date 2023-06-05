package com.example.academicherald.dto;

import lombok.Data;

@Data
public class CommentDto {
    private String text;
    private UserDto user;
    private PublicationDto publication;
}
