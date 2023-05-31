package com.example.academicherald.models.lms;

import com.example.academicherald.models.User;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
