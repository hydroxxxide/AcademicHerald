package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.CourseDto;
import com.example.academicherald.entity.lms.Course;
import com.example.academicherald.mappers.lmsMapper.CourseMapper;
import com.example.academicherald.services.lmsService.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    public CourseDto getById(@PathVariable Long id){
        return courseMapper.convertToDto(courseService.getById(id));
    }

    @GetMapping("/get/all")
    public List<CourseDto> getAll(){
        return courseMapper.convertToDTOList(courseService.getAll());
    }

    @GetMapping("/update")
    public CourseDto update(@RequestBody Course course){
        return courseMapper.convertToDto(courseService.update(course));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        courseService.delete(id);
    }

    @PostMapping("/{courseId}/addStudents")
    public ResponseEntity<?> addStudentsToCourse(@PathVariable Long courseId,
                                                      @RequestBody List<Long> studentIds) {
        try {
            Course updatedCourse = courseService.addStudentsToCourse(courseId, studentIds);
            CourseDto courseDto = courseMapper.convertToDto(updatedCourse);
            return ResponseEntity.ok(courseDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
