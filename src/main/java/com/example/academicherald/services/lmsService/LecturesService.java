package com.example.academicherald.services.lmsService;

import com.example.academicherald.enums.CourseType;
import com.example.academicherald.enums.UserRole;
import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Chapter;
import com.example.academicherald.models.lms.Course;
import com.example.academicherald.models.lms.Lectures;
import com.example.academicherald.repositories.lmsRepo.ChapterRepository;
import com.example.academicherald.repositories.lmsRepo.CourseRepository;
import com.example.academicherald.repositories.lmsRepo.LecturesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturesService {
    private final LecturesRepository lecturesRepository;
    private final CourseRepository courseRepository;
    private final CourseService courseService;
    private final ChapterRepository chapterRepository;

    public LecturesService(LecturesRepository lecturesRepository, CourseRepository courseRepository, CourseService courseService,
                           ChapterRepository chapterRepository) {
        this.lecturesRepository = lecturesRepository;
        this.courseRepository = courseRepository;
        this.courseService = courseService;
        this.chapterRepository = chapterRepository;
    }

    public Lectures createLecture(Lectures lecture, Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(() -> new IllegalArgumentException("Chapter not found with ID: " + chapterId));

        lecture.setChapter(chapter);
        return lecturesRepository.save(lecture);
    }

    public Lectures getById(Long id) {
        return lecturesRepository.findById(id).orElse(null);
    }

}
