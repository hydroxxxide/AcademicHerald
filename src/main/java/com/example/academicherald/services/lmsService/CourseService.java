package com.example.academicherald.services.lmsService;

import com.example.academicherald.enums.UserRole;
import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Course;
import com.example.academicherald.repositories.UserRepository;
import com.example.academicherald.repositories.lmsRepo.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;


    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public Course create(Course course, Long mentorId) {
        User mentor = userRepository.findByIdAndRole(mentorId, UserRole.ROLE_MENTOR);
        course.setMentor(mentor);
        return courseRepository.save(course);
    }
    public Course addStudentsToCourse(Long courseId, List<Long> studentIds) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new IllegalArgumentException("Курс с указанным ID не найден"));

        List<User> studentsId = userRepository.findAllByIdAndRole(studentIds, UserRole.ROLE_STUDENT);
        if (studentsId.isEmpty()) {
            throw new IllegalArgumentException("Нет студентов с указанными ID");
        }

        List<User> existingStudents = course.getStudents();
        for (User student : studentsId) {
            if (!existingStudents.contains(student)) {
                existingStudents.add(student);
            }else{
                throw new IllegalArgumentException("Студенты проходят курс");
            }
        }

        return courseRepository.save(course);
    }

    public Course getById(Long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }

//    public Course update(Course newCourse) {
//        Course oldCourse = courseRepository.getById(newCourse.getId());
//        oldCourse.setType(newCourse.getType());
//        oldCourse.setMentor(newCourse.getMentor());
//        oldCourse.setStudents(newCourse.getStudents());
//        oldCourse.setLectures(newCourse.getLectures());
//        return courseRepository.save(oldCourse);
//    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }


}
