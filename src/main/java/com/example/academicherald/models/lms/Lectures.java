package com.example.academicherald.models.lms;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lectures")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lectures {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String lectureTheme;
    private String text;
}
