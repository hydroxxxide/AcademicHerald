package com.example.academicherald.dto;

import com.example.academicherald.enums.UserRole;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
public class UserDto {
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
