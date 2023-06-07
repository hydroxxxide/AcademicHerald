package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.SubmittedExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmittedExerciseRepository extends JpaRepository<SubmittedExercise, Long> {
}
