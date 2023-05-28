package com.example.academicherald.models.lms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "exercise")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JsonIgnore
    private Chapter chapter;
    private LocalDateTime deadline;
    private String name;
    private String text;
    @OneToMany
    private List<SubmittedExercise> submittedExerciseList;
}
