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
        Material materials = exercise.getMaterial();
        Material material = new Material();
        material.setTheme(materials.getTheme());
        material.setText(materials.getText());
        material = materialRepository.save(material);

        exercise.setChapter(chapter);
        exercise.setMaterial(material);
        return exerciseRepository.save(exercise);
    }

    public void submitExercise(Long exerciseId, Long studentId, SubmittedExercise submittedExercise) {
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
        }
        SubmittedExercise savedSubmission = submittedExerciseRepository.save(submittedExercise);

        List<SubmittedExercise> submittedExercises = exercise.getSubmittedExerciseList();
        submittedExercises.add(savedSubmission);

        exerciseRepository.save(exercise);
    }

    public Exercise getById(Long id) {
        return exerciseRepository.findByIdAndRdtIsNull(id);
    }

    public List<Exercise> getAllExercise() {
        return exerciseRepository.findAllByRdtIsNull();
    }

    public List<Exercise> findByChapter(Long chapterId) {
        return exerciseRepository.findAllByChapterIdAndRdtIsNull(chapterId);
    }

    public Exercise updateExercise(Exercise newExercise) {
        Exercise oldExercise = exerciseRepository.findByIdAndRdtIsNull(newExercise.getId());
        oldExercise.setDeadline(newExercise.getDeadline());
        oldExercise.setTitle(newExercise.getTitle());

        return exerciseRepository.save(oldExercise);
    }

    public void deleteExercise(Long id) {
        Exercise exercise = getById(id);
        exercise.setRdt(LocalDateTime.now());
        exerciseRepository.save(exercise);
    }
}

