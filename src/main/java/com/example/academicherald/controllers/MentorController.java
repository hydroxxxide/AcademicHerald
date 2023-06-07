package com.example.academicherald.controllers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.dto.lmsDto.*;
import com.example.academicherald.entity.lms.Chapter;
import com.example.academicherald.entity.lms.Course;
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
    private final SubmittedExerciseService submittedExerciseService;
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

    // управление курсами

    @PostMapping("/course/create")
    public CourseDto createCourse(@RequestBody CourseDto courseDto,
                                  @RequestParam Long mentorId) {
        Course course = courseMapper.convertToEntity(courseDto);
        Course createdCourse = courseService.create(course, mentorId);
        return courseMapper.convertToDto(createdCourse);
    }

    @GetMapping("/course/get/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseMapper.convertToDto(courseService.getById(id));
    }

    @GetMapping("/course/get/all")
    public List<CourseDto> getAllCourses() {
        return courseMapper.convertToDTOList(courseService.getAll());
    }

    @GetMapping("/course/update")
    public CourseDto updateCourse(@RequestBody CourseDto courseDto) {
        Course course = courseMapper.convertToEntity(courseDto);
        return courseMapper.convertToDto(courseService.update(course));
    }

    @GetMapping("/course/delete/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }

    @GetMapping("/course/students/{courseId}")
    public List<UserDto> getAllStudentsInCourse(@PathVariable Long courseId) {
        return userMapper.convertToDTOList(courseService.getCourseStudents(courseId));
    }

    @PostMapping("/chapter/create")
    public ChapterDto createChapter(@RequestBody ChapterDto chapterDto,
                                    @RequestParam Long courseId) {
        Chapter chapter = chapterMapper.convertToEntity(chapterDto);
        Chapter createdChapter = chapterService.createChapter(chapter, courseId);
        return chapterMapper.convertToDto(createdChapter);
    }

    @GetMapping("/chapter/get/{id}")
    public ChapterDto getChapterById(@PathVariable Long id) {
        return chapterMapper.convertToDto(chapterService.getById(id));
    }

    @GetMapping("/chapter/get/all")
    public List<ChapterDto> getAllChapters() {
        return chapterMapper.convertToDTOList(chapterService.getAll());
    }

    @PutMapping("/chapter/update")
    public ChapterDto updateChapter(@RequestBody Chapter chapter) {
        return chapterMapper.convertToDto(chapterService.updateChapter(chapter));
    }

    @DeleteMapping("/chapter/delete/{id}")
    public void deleteChapter(@PathVariable Long id) {
        chapterService.delete(id);
    }

    @PostMapping("/lecture/create")
    public LecturesDto createLecture(@RequestBody LecturesDto lecturesDto,
                                     @RequestParam Long chapterId) {
        Lectures lectures = lecturesMapper.convertToEntity(lecturesDto);
        Lectures createdLectures = lecturesService.createLecture(lectures, chapterId);
        return lecturesMapper.convertToDto(createdLectures);
    }

    @PutMapping("/lecture/update")
    public LecturesDto update(@RequestBody Lectures lectures) {
        return lecturesMapper.convertToDto(lecturesService.updateLecture(lectures));
    }

    @DeleteMapping("/lecture/delete/{id}")
    public void delete(@PathVariable Long id) {
        lecturesService.deleteLecture(id);
    }

    @PostMapping("/exercise/create")
    public ExerciseDto createExercise(@RequestBody ExerciseDto exerciseDto,
                                      @RequestParam Long chapterId) {
        Exercise exercise = exerciseMapper.convertToEntity(exerciseDto);
        Exercise createdExercise = exerciseService.createExercise(exercise, chapterId);
        return exerciseMapper.convertToDto(createdExercise);
    }

    @PutMapping("/exercise/update")
    public ExerciseDto update(@RequestBody Exercise exercise) {
        return exerciseMapper.convertToDto(exerciseService.updateExercise(exercise));
    }

    @DeleteMapping("/exercise/delete/{id}")
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
