package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.Lectures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LecturesRepository extends JpaRepository<Lectures, Long> {
    List<Lectures> findByChapterId(Long courseId);

}
