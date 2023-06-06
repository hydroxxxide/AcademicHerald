package com.example.academicherald.services.lmsService;

import com.example.academicherald.entity.lms.Lectures;
import com.example.academicherald.entity.lms.SubmittedExercise;
import com.example.academicherald.repositories.lmsRepo.ExerciseRepository;
import com.example.academicherald.repositories.lmsRepo.SubmittedExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmittedExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final SubmittedExerciseRepository submittedExerciseRepository;

    public SubmittedExerciseService(ExerciseRepository exerciseRepository, SubmittedExerciseRepository submittedExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.submittedExerciseRepository = submittedExerciseRepository;
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
}
