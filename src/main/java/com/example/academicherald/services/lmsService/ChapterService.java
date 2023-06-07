package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.lms.Chapter;
import com.example.academicherald.entity.lms.Course;
import com.example.academicherald.repositories.lmsRepo.ChapterRepository;
import com.example.academicherald.repositories.lmsRepo.CourseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final CourseRepository courseRepository;

    public ChapterService(ChapterRepository chapterRepository, CourseRepository courseRepository) {
        this.chapterRepository = chapterRepository;
        this.courseRepository = courseRepository;
    }


    public Chapter createChapter(Chapter chapter, Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        chapter.setCourse(course);
        return chapterRepository.save(chapter);
    }

    public Chapter updateChapter(Chapter newChapter) {
        Chapter oldChapter = getById(newChapter.getId());
        oldChapter.setName(newChapter.getName());
        return chapterRepository.save(oldChapter);
    }

    public Chapter getById(Long id) {
        return chapterRepository.findByIdAndRdtIsNull(id);
    }

    public List<Chapter> getAll() {
        return chapterRepository.findAllByRdtIsNull();
    }

    public void delete(Long id) {
        Chapter chapter = getById(id);
        chapter.setRdt(LocalDateTime.now());
        chapterRepository.save(chapter);
    }

    public List<Chapter> getAllChaptersByCourse(Long courseId) {
        return chapterRepository.findByCourseIdAndRdtIsNull(courseId);
    }
}
