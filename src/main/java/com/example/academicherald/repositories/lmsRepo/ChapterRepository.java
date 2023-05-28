package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.models.lms.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {

}
