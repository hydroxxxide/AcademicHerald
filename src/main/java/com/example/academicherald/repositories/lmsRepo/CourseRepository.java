package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findAllByRdtIsNull();

    Course findByIdAndRdtIsNull(Long id);
}
