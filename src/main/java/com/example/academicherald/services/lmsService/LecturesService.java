package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.lms.Chapter;
import com.example.academicherald.entity.lms.Exercise;
import com.example.academicherald.entity.lms.Lectures;
import com.example.academicherald.entity.lms.Material;
import com.example.academicherald.repositories.lmsRepo.ChapterRepository;
import com.example.academicherald.repositories.lmsRepo.LecturesRepository;
import com.example.academicherald.repositories.lmsRepo.MaterialRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LecturesService {
    private final LecturesRepository lecturesRepository;

    private final ChapterRepository chapterRepository;
    private final MaterialRepository materialRepository;

    public LecturesService(LecturesRepository lecturesRepository,
                           ChapterRepository chapterRepository, MaterialRepository materialRepository) {
        this.lecturesRepository = lecturesRepository;
        this.chapterRepository = chapterRepository;
        this.materialRepository = materialRepository;
    }

    public Lectures createLecture(Lectures lecture, Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId).orElseThrow(() -> new IllegalArgumentException("Chapter not found with ID: " + chapterId));
        Material material1 = lecture.getMaterial();
        Material material = new Material();
        material.setTheme(material1.getTheme());
        material.setText(material1.getText());
        material = materialRepository.save(material);

        lecture.setChapter(chapter);
        lecture.setMaterial(material);
        return lecturesRepository.save(lecture);
    }

    public Lectures getById(Long id) {
        return lecturesRepository.findById(id).orElse(null);
    }
    public List<Lectures> findByChapter(Long chapterId) {
        return lecturesRepository.findByChapterId(chapterId);
    }

    public Lectures updateLecture(Lectures newLecture){
        Lectures oldLecture = lecturesRepository.getById(newLecture.getId());
        oldLecture.setChapter(newLecture.getChapter());
        oldLecture.setTitle(newLecture.getTitle());
        oldLecture.setMaterial(newLecture.getMaterial());
        return lecturesRepository.save(oldLecture);
    }

//    public void delete(Long id){
//
//    }
}
