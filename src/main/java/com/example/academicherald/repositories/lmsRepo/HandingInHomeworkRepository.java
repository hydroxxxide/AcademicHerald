package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.models.lms.HandingInHomework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HandingInHomeworkRepository extends JpaRepository<HandingInHomework, Long> {
}
