package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Exercise;
import lombok.Data;

@Data
public class SubmittedExerciseDto {
    private Exercise exerciseId;
    private User studentId;
    private String linkGitHubHw;
}
