package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.User;
import com.example.academicherald.entity.lms.*;
import com.example.academicherald.repositories.UserRepository;
import com.example.academicherald.repositories.lmsRepo.ChapterRepository;
import com.example.academicherald.repositories.lmsRepo.ExerciseRepository;
import com.example.academicherald.repositories.lmsRepo.MaterialRepository;
import com.example.academicherald.repositories.lmsRepo.SubmittedExerciseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final UserRepository userRepository;
    private final ChapterRepository chapterRepository;
    private final SubmittedExerciseRepository submittedExerciseRepository;
    private final MaterialRepository materialRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, UserRepository userRepository, ChapterRepository chapterRepository, SubmittedExerciseRepository submittedExerciseRepository, MaterialRepository materialRepository) {
        this.exerciseRepository = exerciseRepository;
        this.userRepository = userRepository;
        this.chapterRepository = chapterRepository;
        this.submittedExerciseRepository = submittedExerciseRepository;
        this.materialRepository = materialRepository;
    }

    public Exercise createExercise(Exercise exercise, Long chapterId) {
        Chapter chapter = chapterRepository.findById(chapterId).orElse(null);
        Material material1 = exercise.getMaterial();
        Material material = new Material();
        material.setTheme(material1.getTheme());
        material.setText(material1.getText());
        material = materialRepository.save(material);

        exercise.setChapter(chapter);
        exercise.setMaterial(material);
        return exerciseRepository.save(exercise);
    }

    public SubmittedExercise submitExercise(Long exerciseId, Long studentId, SubmittedExercise submittedExercise) {
        Exercise exercise = exerciseRepository.findById(exerciseId)
                .orElseThrow(() -> new IllegalArgumentException("Задание с указанным ID не найдено"));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Студент с указанным ID не найден"));

        Course course = exercise.getChapter().getCourse();
        List<User> students = course.getStudents();

        if (!students.contains(student)) {
            throw new IllegalArgumentException("Студент не является участником курса");
        }

        submittedExercise.setExerciseId(exercise);
        submittedExercise.setStudentId(student);

        LocalDateTime deadline = exercise.getDeadline();
        LocalDateTime currentDateTime = LocalDateTime.now();

        if (currentDateTime.isAfter(deadline)) {
            throw new IllegalArgumentException("Вы сдали домашнее задание с опозданием");
            // Здесь вы можете отправить сообщение студенту о его опоздании или выполнить другие действия
        }
        SubmittedExercise savedSubmission = submittedExerciseRepository.save(submittedExercise);

        List<SubmittedExercise> submittedExercises = exercise.getSubmittedExerciseList();
        submittedExercises.add(savedSubmission);

        exerciseRepository.save(exercise);

        return savedSubmission;
    }


    public static List<Exercise> findByChapter(Chapter chapter) {
        // код для поиска заданий по главе
        return null;
    }
}