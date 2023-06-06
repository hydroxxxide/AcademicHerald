package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.ExerciseDto;
import com.example.academicherald.dto.lmsDto.SubmittedExerciseDto;
import com.example.academicherald.entity.lms.Exercise;
import com.example.academicherald.entity.lms.SubmittedExercise;
import com.example.academicherald.mappers.lmsMapper.ExerciseMapper;
import com.example.academicherald.mappers.lmsMapper.SubmittedExerciseMapper;
import com.example.academicherald.services.lmsService.ExerciseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/update")
    public ExerciseDto update(@RequestBody Exercise exercise){
        return exerciseMapper.convertToDto(exerciseService.updateExercise(exercise));
    }

    @GetMapping("/get/{id}")
    public ExerciseDto getById(@PathVariable Long id){
        return exerciseMapper.convertToDto(exerciseService.getById(id));
    }



    @DeleteMapping("/delete/{id}")
    public void deleteExercise(@PathVariable Long id){
        exerciseService.deleteExercise(id);
    }

    @GetMapping("/getAllByChapter/{chapterId}")
    public List<ExerciseDto> findByChapter(@PathVariable Long chapterId){
        return exerciseMapper.convertToDTOList(exerciseService.findByChapter(chapterId));
    }

    @PostMapping("/{exerciseId}/submit")
    public ResponseEntity<String> submitExercise(
            @PathVariable Long exerciseId,
            @RequestParam Long studentId,
            @RequestBody SubmittedExerciseDto submittedExerciseDto
    ) {
        try {
            SubmittedExercise submittedExercise = submittedExerciseMapper.convertToEntity(submittedExerciseDto);
            exerciseService.submitExercise(exerciseId, studentId, submittedExercise);
            return ResponseEntity.ok("Вы сдали домашнюю работу!");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}