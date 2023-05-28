package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Exercise;
import com.example.academicherald.models.lms.SubmittedExercise;
import com.example.academicherald.services.UserService;
import com.example.academicherald.services.lmsService.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
public class ExerciseController {
    private final ExerciseService exerciseService;
    private final UserService userService;

    public ExerciseController(ExerciseService exerciseService, UserService userService) {
        this.exerciseService = exerciseService;
        this.userService = userService;
    }

}
