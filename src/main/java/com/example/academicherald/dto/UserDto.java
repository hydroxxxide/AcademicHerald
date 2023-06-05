package com.example.academicherald.dto;

import com.example.academicherald.enums.UserRole;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Data;

@Data
public class UserDto {
    private String username;
    private String email;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}
