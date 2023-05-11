package com.example.academicherald.dto;

import com.example.academicherald.models.User;
import lombok.Data;

@Data
public class CommentDTO {
    private User user;
    private String text;
}
