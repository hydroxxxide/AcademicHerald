package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {
    List<Exercise> findAllByChapterIdAndRdtIsNull(Long chapterId);

    Exercise findByIdAndRdtIsNull(Long id);

    List<Exercise> findAllByRdtIsNull();
}
