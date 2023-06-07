package com.example.academicherald.entity.lms;

import com.example.academicherald.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "submitexercise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SubmittedExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "exercise_id")
    private Exercise exerciseId;

    @OneToOne
    @JoinColumn(name = "student_id")
    private User studentId;

    private String linkGitHubHw;

    private LocalDateTime rdt;
    private Boolean pass = false;
    private String feedback;

}
