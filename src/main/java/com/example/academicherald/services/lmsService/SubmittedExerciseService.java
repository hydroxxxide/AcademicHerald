package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.User;
import com.example.academicherald.entity.lms.*;
import com.example.academicherald.repositories.lmsRepo.ChapterRepository;
import com.example.academicherald.repositories.lmsRepo.ExerciseRepository;
import com.example.academicherald.repositories.lmsRepo.SubmittedExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmittedExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final SubmittedExerciseRepository submittedExerciseRepository;
    private final ChapterRepository chapterRepository;

    public SubmittedExerciseService(ExerciseRepository exerciseRepository, SubmittedExerciseRepository submittedExerciseRepository,
                                    ChapterRepository chapterRepository) {
        this.exerciseRepository = exerciseRepository;
        this.submittedExerciseRepository = submittedExerciseRepository;
        this.chapterRepository = chapterRepository;
    }

    public SubmittedExercise getById(Long id) {
        return submittedExerciseRepository.findById(id).orElse(null);
    }
    public SubmittedExercise updateSubmittedExercise(SubmittedExercise newSubmittedExercise){
        SubmittedExercise oldSubmittedExercise = submittedExerciseRepository.getById(newSubmittedExercise.getId());
        oldSubmittedExercise.setExerciseId(newSubmittedExercise.getExerciseId());
        oldSubmittedExercise.setStudentId(newSubmittedExercise.getStudentId());
        oldSubmittedExercise.setLinkGitHubHw(newSubmittedExercise.getLinkGitHubHw());
        return submittedExerciseRepository.save(oldSubmittedExercise);
    }

    public SubmittedExercise reviewHomework(Long submittedExerciseId, Boolean pass, String feedback) {
        SubmittedExercise submittedExercise = submittedExerciseRepository.findById(submittedExerciseId).orElse(null);
        submittedExercise.setPass(pass);
        submittedExercise.setFeedback(feedback);
        return submittedExerciseRepository.save(submittedExercise);
    }

    public List<SubmittedExercise> allSubmittedExercise(Long exerciseId) {
        Exercise exercise = exerciseRepository.findById(exerciseId).orElse(null);
        return exercise.getSubmittedExerciseList();
    }
}
