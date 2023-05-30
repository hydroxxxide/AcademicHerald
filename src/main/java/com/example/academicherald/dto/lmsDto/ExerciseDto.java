package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.models.lms.Chapter;
import com.example.academicherald.models.lms.SubmittedExercise;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class ExerciseDto {
    private LocalDateTime deadline;
    private String name;
    private String text;
    private List<SubmittedExercise> submittedExerciseList;
}
