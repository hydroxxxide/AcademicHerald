package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.dto.lmsDto.ChapterDto;
import com.example.academicherald.dto.lmsDto.ExerciseDto;
import com.example.academicherald.dto.lmsDto.LecturesDto;
import com.example.academicherald.dto.lmsDto.SubmittedExerciseDto;
import com.example.academicherald.entity.lms.Chapter;
import com.example.academicherald.entity.lms.Exercise;
import com.example.academicherald.entity.lms.Lectures;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.mappers.lmsMapper.*;
import com.example.academicherald.repositories.lmsRepo.SubmittedExerciseRepository;
import com.example.academicherald.services.lmsService.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mentor")
public class MentorController {
    private final ExerciseService exerciseService;
    private final ExerciseMapper exerciseMapper;
    private final SubmittedExerciseMapper submittedExerciseMapper;
    private SubmittedExerciseService submittedExerciseService;
    private final LecturesService lecturesService;
    private final LecturesMapper lecturesMapper;

    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    private final CourseService courseService;

    private final CourseMapper courseMapper;
    private final UserMapper userMapper;
    private final SubmittedExerciseRepository submittedExerciseRepository;

    public MentorController(ExerciseService exerciseService, ExerciseMapper exerciseMapper, SubmittedExerciseMapper submittedExerciseMapper, SubmittedExerciseService submittedExerciseService, LecturesService lecturesService, LecturesMapper lecturesMapper, ChapterService chapterService, ChapterMapper chapterMapper, CourseService courseService, CourseMapper courseMapper, UserMapper userMapper,
                            SubmittedExerciseRepository submittedExerciseRepository) {
        this.exerciseService = exerciseService;
        this.exerciseMapper = exerciseMapper;
        this.submittedExerciseMapper = submittedExerciseMapper;
        this.submittedExerciseService = submittedExerciseService;
        this.lecturesService = lecturesService;
        this.lecturesMapper = lecturesMapper;
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
        this.courseService = courseService;
        this.courseMapper = courseMapper;
        this.userMapper = userMapper;
        this.submittedExerciseRepository = submittedExerciseRepository;
    }


    @GetMapping("/allStudentsInCourse/{courseId}")
    public List<UserDto> getAllStudentsInCourse(@PathVariable Long courseId) {
        return userMapper.convertToDTOList(courseService.getCourseStudents(courseId));
    }

    @PostMapping("/createChapter")
    public ChapterDto createChapter(@RequestBody ChapterDto chapterDto,
                                    @RequestParam Long courseId) {
        Chapter chapter = chapterMapper.convertToEntity(chapterDto);
        Chapter createdChapter = chapterService.createChapter(chapter, courseId);
        return chapterMapper.convertToDto(createdChapter);
    }

    @PutMapping("/updateChapter")
    public ChapterDto updateChapter(@RequestBody Chapter chapter) {
        return chapterMapper.convertToDto(chapterService.updateChapter(chapter));
    }

    @DeleteMapping("/deleteChapter/{id}")
    public void deleteChapter(@PathVariable Long id) {
        chapterService.delete(id);
    }

    @PostMapping("/createLecture")
    public LecturesDto createLecture(@RequestBody LecturesDto lecturesDto,
                                     @RequestParam Long chapterId) {
        Lectures lectures = lecturesMapper.convertToEntity(lecturesDto);
        Lectures createdLectures = lecturesService.createLecture(lectures, chapterId);
        return lecturesMapper.convertToDto(createdLectures);
    }

    @PutMapping("/updateLecture")
    public LecturesDto update(@RequestBody Lectures lectures) {
        return lecturesMapper.convertToDto(lecturesService.updateLecture(lectures));
    }

    @DeleteMapping("/deleteLecture/{id}")
    public void delete(@PathVariable Long id) {
        lecturesService.deleteLecture(id);
    }

    @PostMapping("/createExercise")
    public ExerciseDto createExercise(@RequestBody ExerciseDto exerciseDto,
                                      @RequestParam Long chapterId) {
        Exercise exercise = exerciseMapper.convertToEntity(exerciseDto);
        Exercise createdExercise = exerciseService.createExercise(exercise, chapterId);
        return exerciseMapper.convertToDto(createdExercise);
    }

    @PutMapping("/updateExercise")
    public ExerciseDto update(@RequestBody Exercise exercise) {
        return exerciseMapper.convertToDto(exerciseService.updateExercise(exercise));
    }

    @DeleteMapping("/delete/{id}")
    public void deleteExercise(@PathVariable Long id) {
        exerciseService.deleteExercise(id);
    }

    @PostMapping("/review/{submittedExerciseId}")
    public ResponseEntity<String> reviewHomework(
            @PathVariable Long submittedExerciseId,
            @RequestBody Boolean pass,
            @RequestBody String feedback
    ) {
        submittedExerciseService.reviewHomework(submittedExerciseId, pass, feedback);
        return ResponseEntity.ok("Домашняя работа проверена!");
    }


    @GetMapping("/allSubmittedExercse")
    public List<SubmittedExerciseDto> allSubmittedExercise(@PathVariable Long exerciseId) {
        return submittedExerciseMapper.convertToDTOList(submittedExerciseService.allSubmittedExercise(exerciseId));
    }

}
