package com.example.academicherald.requests;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class RegistrationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String email;

    // Дополнительные поля, такие как имя, фамилия, электронная почта и т.д.

    // Конструкторы, геттеры и сеттеры

    // Дополнительные методы, если необходимо
}
