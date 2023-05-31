package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.enums.CourseType;
import com.example.academicherald.models.lms.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    Course findByType(CourseType type);

}
