package com.example.academicherald.entity.lms;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "chapter")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private Course course;

    @OneToMany
    private List<Lectures> lectures;

    @OneToMany
    private List<Exercise> exercises;

    public void setCourse(Course course) {
        this.course = course;
        course.getChapters().add(this); // Добавляем текущую главу в список глав курса
    }

    private LocalDateTime rdt;
}
