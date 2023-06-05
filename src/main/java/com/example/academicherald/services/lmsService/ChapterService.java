package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.lms.Chapter;
import com.example.academicherald.entity.lms.Course;
import com.example.academicherald.entity.lms.Exercise;
import com.example.academicherald.entity.lms.Lectures;
import com.example.academicherald.repositories.lmsRepo.ChapterRepository;
import com.example.academicherald.repositories.lmsRepo.CourseRepository;
import com.example.academicherald.repositories.lmsRepo.LecturesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChapterService {
    private final ChapterRepository chapterRepository;
    private final LecturesRepository lecturesRepository;

    private final CourseRepository courseRepository;
    public ChapterService(ChapterRepository chapterRepository,
                          LecturesRepository lecturesRepository, CourseRepository courseRepository) {
        this.chapterRepository = chapterRepository;
        this.lecturesRepository = lecturesRepository;
        this.courseRepository = courseRepository;
    }

    public Chapter createChapter(Chapter chapter, Long courseId) {
        Course course = courseRepository.findById(courseId).orElse(null);
        assert course != null;
        chapter.setCourse(course);
        return chapterRepository.save(chapter);
    }
    public Chapter addLectures(Long chapterId, List<Long> lectureIds) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new IllegalArgumentException("Chapter not found with ID: " + chapterId));

        List<Lectures> lectures = lecturesRepository.findAllById(lectureIds);
        if (lectures.isEmpty()) {
            throw new IllegalArgumentException("No lectures found with the specified IDs");
        }
        chapter.getLectures().addAll(lectures);

        return chapterRepository.save(chapter);
    }

    public Chapter addExercises(Long chapterId, List<Exercise> exercises) {
        Chapter chapter = chapterRepository.findById(chapterId)
                .orElseThrow(() -> new IllegalArgumentException("Chapter not found with ID: " + chapterId));

        chapter.getExercises().addAll(exercises);

        return chapterRepository.save(chapter);
    }
}
