package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.enums.UserRole;
import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Long> {

}
