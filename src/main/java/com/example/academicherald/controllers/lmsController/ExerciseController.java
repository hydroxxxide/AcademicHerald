package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.ExerciseDto;
import com.example.academicherald.mappers.lmsMapper.ExerciseMapper;
import com.example.academicherald.models.lms.Exercise;
import com.example.academicherald.models.lms.SubmittedExercise;
import com.example.academicherald.services.UserService;
import com.example.academicherald.services.lmsService.ExerciseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;
    private final UserService userService;

    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper, UserService userService) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        this.userService = userService;
    }

    @PostMapping("/create")
    public ExerciseDto createExercise(@RequestBody ExerciseDto exerciseDto,
                                      @RequestParam Long chapterId) {
        Exercise exercise = exerciseMapper.convertToEntity(exerciseDto);
        Exercise createdExercise = exerciseService.createExercise(exercise, chapterId);
    return exerciseMapper.convertToDto(createdExercise);
    }

    @PostMapping("/{exerciseId}/submit")
    public ResponseEntity<String> submitExercise(
            @PathVariable Long exerciseId,
            @RequestParam Long studentId,
            @RequestBody SubmittedExercise submittedExercise
    ) {
        try {
            exerciseService.submitExercise(exerciseId, studentId, submittedExercise);
            return ResponseEntity.ok("Вы сдали домашнюю работу!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}