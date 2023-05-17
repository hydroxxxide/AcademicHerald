package com.example.academicherald.dto;

import com.example.academicherald.models.Publication;
import com.example.academicherald.models.User;
import lombok.Data;

@Data
public class CommentDto {
    private String text;
    private User user;
    private Publication publication;
}
