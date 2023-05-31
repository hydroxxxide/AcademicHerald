package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Exercise;
import lombok.Data;

@Data
public class SubmittedExerciseDto {
    private ExerciseDto exerciseId;
    private UserDto studentId;
    private String linkGitHubHw;
}
