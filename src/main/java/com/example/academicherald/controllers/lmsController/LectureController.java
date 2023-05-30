package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.CourseDto;
import com.example.academicherald.dto.lmsDto.LecturesDto;
import com.example.academicherald.enums.CourseType;
import com.example.academicherald.mappers.lmsMapper.LecturesMapper;
import com.example.academicherald.models.lms.Course;
import com.example.academicherald.models.lms.Lectures;
import com.example.academicherald.services.lmsService.CourseService;
import com.example.academicherald.services.lmsService.LecturesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LecturesService lecturesService;
    private final LecturesMapper lecturesMapper;
    private final CourseService courseService;

    public LectureController(LecturesService lecturesService, LecturesMapper lecturesMapper, CourseService courseService) {
        this.lecturesService = lecturesService;
        this.lecturesMapper = lecturesMapper;
        this.courseService = courseService;
    }
    @PostMapping("/create")
    public LecturesDto createLecture(@RequestBody LecturesDto lecturesDto,
                                     @RequestParam Long chapterId) {
        Lectures lectures = lecturesMapper.convertToEntity(lecturesDto);
        Lectures createdLectures = lecturesService.createLecture(lectures, chapterId);
        return lecturesMapper.convertToDto(createdLectures);
    }
    @GetMapping("/get/{id}")
    public LecturesDto getById(@PathVariable Long id) {
        return lecturesMapper.convertToDto(lecturesService.getById(id));
    }
}
