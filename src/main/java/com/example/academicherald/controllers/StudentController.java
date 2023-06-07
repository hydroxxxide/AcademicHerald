package com.example.academicherald.controllers;

import com.example.academicherald.dto.lmsDto.*;
import com.example.academicherald.entity.lms.SubmittedExercise;
import com.example.academicherald.mappers.lmsMapper.*;
import com.example.academicherald.services.lmsService.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final CourseService courseService;
    private final CourseMapper courseMapper;
    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;
    private final SubmittedExerciseMapper submittedExerciseMapper;
    private final LecturesService lecturesService;
    private final LecturesMapper lecturesMapper;
    private final SubmittedExerciseService submittedExerciseService;


    public StudentController(CourseService courseService, CourseMapper courseMapper, ChapterService chapterService, ChapterMapper chapterMapper, ExerciseService exerciseService, ExerciseMapper exerciseMapper, SubmittedExerciseMapper submittedExerciseMapper, LecturesService lecturesService, LecturesMapper lecturesMapper, SubmittedExerciseService submittedExerciseService) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        this.submittedExerciseMapper = submittedExerciseMapper;
        this.lecturesService = lecturesService;
        this.lecturesMapper = lecturesMapper;
        this.submittedExerciseService = submittedExerciseService;
    }

    @GetMapping("/getCourse/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseMapper.convertToDto(courseService.getById(id));
    }

    @GetMapping("/getChapter/{id}")
    public ChapterDto getChapterById(@PathVariable Long id) {
        return chapterMapper.convertToDto(chapterService.getById(id));
    }

    @GetMapping("/getAllLecturesByChapter/{chapterId}")
    public List<LecturesDto> findByLectureInChapter(@PathVariable Long chapterId) {
        return lecturesMapper.convertToDTOList(lecturesService.findByChapter(chapterId));
    }

    @GetMapping("/getLecture/{id}")
    public LecturesDto getLectureById(@PathVariable Long id) {
        return lecturesMapper.convertToDto(lecturesService.getById(id));
    }

    @GetMapping("/getAllExerciseByChapter/{chapterId}")
    public List<ExerciseDto> findByExerciseInChapter(@PathVariable Long chapterId) {
        return exerciseMapper.convertToDTOList(exerciseService.findByChapter(chapterId));
    }

    @GetMapping("/getExercise/{id}")
    public ExerciseDto getExerciseById(@PathVariable Long id) {
        return exerciseMapper.convertToDto(exerciseService.getById(id));
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

    @GetMapping("/geSubmittedExercise/{id}")
    public SubmittedExerciseDto geSubmittedExerciseById(@PathVariable Long id) {
        return submittedExerciseMapper.convertToDto(submittedExerciseService.getById(id));
    }
}
