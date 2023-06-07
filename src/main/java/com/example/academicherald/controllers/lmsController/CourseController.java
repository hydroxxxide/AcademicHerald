package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.CourseDto;
import com.example.academicherald.entity.lms.Course;
import com.example.academicherald.mappers.lmsMapper.CourseMapper;
import com.example.academicherald.services.lmsService.CourseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class CourseController {

    private final CourseService courseService;
    private final CourseMapper courseMapper;

    public CourseController(CourseService courseService, CourseMapper courseMapper) {
        this.courseService = courseService;
        this.courseMapper = courseMapper;
    }

    @PostMapping("/create")
    public CourseDto createCourse(@RequestBody CourseDto courseDto,
                                  @RequestParam Long mentorId) {
        Course course = courseMapper.convertToEntity(courseDto);
        Course createdCourse = courseService.create(course, mentorId);
        return courseMapper.convertToDto(createdCourse);
    }

    @GetMapping("/get/{id}")
    public CourseDto getCourseById(@PathVariable Long id) {
        return courseMapper.convertToDto(courseService.getById(id));
    }

    @GetMapping("/get/all")
    public List<CourseDto> getAllCourses() {
        return courseMapper.convertToDTOList(courseService.getAll());
    }

}
