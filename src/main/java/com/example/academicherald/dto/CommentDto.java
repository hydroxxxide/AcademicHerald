package com.example.academicherald.dto;

import com.example.academicherald.entity.Publication;
import com.example.academicherald.entity.User;
import lombok.Data;

@Data
public class CommentDto {
    private String text;
    private User user;
    private Publication publication;
}
