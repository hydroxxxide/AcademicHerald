package com.example.academicherald.dto.lmsDto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ExerciseDto {
    private LocalDateTime deadline;
    private String title;
    private MaterialDto material;
    private List<SubmittedExerciseDto> submittedExerciseList;
}
