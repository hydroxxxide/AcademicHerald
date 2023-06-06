package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.Exercise;
import com.example.academicherald.entity.lms.SubmittedExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubmittedExerciseRepository extends JpaRepository<SubmittedExercise, Long> {

}
