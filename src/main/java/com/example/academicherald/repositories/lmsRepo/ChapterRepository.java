package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    Chapter findByIdAndRdtIsNull(Long id);

    List<Chapter> findByCourseIdAndRdtIsNull(Long courseId);

    List<Chapter> findAllByRdtIsNull();
}
