package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.ExerciseDto;
import com.example.academicherald.entity.lms.Exercise;
import com.example.academicherald.mappers.lmsMapper.ExerciseMapper;
import com.example.academicherald.mappers.lmsMapper.SubmittedExerciseMapper;
import com.example.academicherald.services.lmsService.ExerciseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/exercises")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class ExerciseController {
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;
    private final SubmittedExerciseMapper submittedExerciseMapper;

    public ExerciseController(ExerciseService exerciseService, ExerciseMapper exerciseMapper, SubmittedExerciseMapper submittedExerciseMapper) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        this.submittedExerciseMapper = submittedExerciseMapper;
    }

    @PostMapping("/create")
    public ExerciseDto createExercise(@RequestBody ExerciseDto exerciseDto,
                                      @RequestParam Long chapterId) {
        Exercise exercise = exerciseMapper.convertToEntity(exerciseDto);
        Exercise createdExercise = exerciseService.createExercise(exercise, chapterId);
        return exerciseMapper.convertToDto(createdExercise);
    }


}