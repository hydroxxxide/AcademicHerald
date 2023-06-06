package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.lms.Chapter;
import com.example.academicherald.entity.lms.Course;
import com.example.academicherald.repositories.lmsRepo.ChapterRepository;
import com.example.academicherald.repositories.lmsRepo.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Chapter oldChapter = chapterRepository.getById(newChapter.getId());
        oldChapter.setName(newChapter.getName());
        oldChapter.setCourse(newChapter.getCourse());
        oldChapter.setExercises(newChapter.getExercises());
        return chapterRepository.save(oldChapter);

    }

    public Optional<Chapter> getById(Long id) {
        return chapterRepository.findById(id);
    }

    public List<Chapter> getAllChapterByCourse(Long courseId) {
        return chapterRepository.findByCourseId(courseId);
    }

}
