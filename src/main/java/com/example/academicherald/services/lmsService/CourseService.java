package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.User;
import com.example.academicherald.entity.lms.Course;
import com.example.academicherald.enums.UserRole;
import com.example.academicherald.repositories.UserRepository;
import com.example.academicherald.repositories.lmsRepo.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if(mentor == null){
            throw new IllegalArgumentException("Укажите ментора курса");
        }
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
    public List<User> getCourseStudents(Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
       return course.getStudents();
    }

    public Course getById(Long id) {
        return courseRepository.findByIdAndRdtIsNull(id);
    }

    public List<Course> getAll() {
        return courseRepository.findAllByRdtIsNull();
    }

    public Course update(Course newCourse) {
        Course oldCourse = getById(newCourse.getId());
        oldCourse.setType(newCourse.getType());
        oldCourse.setMentor(newCourse.getMentor());
        oldCourse.setStudents(newCourse.getStudents());
        oldCourse.setChapters(newCourse.getChapters());
        return courseRepository.save(oldCourse);
    }

    public void delete(Long id) {
        Course course = getById(id);
        course.setRdt(LocalDateTime.now());
        courseRepository.save(course);
    }
}
