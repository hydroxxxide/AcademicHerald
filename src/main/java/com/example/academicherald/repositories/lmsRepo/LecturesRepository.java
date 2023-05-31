package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.Lectures;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LecturesRepository extends JpaRepository<Lectures, Long> {
}
