package com.example.academicherald.models.lms;

import com.example.academicherald.enums.CourseType;
import com.example.academicherald.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "course")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private CourseType courseType;
    @ManyToOne
    private User mentor;
    @OneToMany
    private List<User> students;
    @OneToMany
    private List<Lectures> lectures;
}
