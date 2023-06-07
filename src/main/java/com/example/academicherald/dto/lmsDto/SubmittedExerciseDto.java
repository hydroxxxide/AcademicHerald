package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.dto.UserDto;
import lombok.Data;

@Data
public class SubmittedExerciseDto {
    private UserDto studentId;
    private String linkGitHubHw;
    private Boolean pass = false;
    private String feedback;
}
