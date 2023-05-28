package com.example.academicherald.services.lmsService;

import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Exercise;
import com.example.academicherald.models.lms.SubmittedExercise;
import com.example.academicherald.repositories.lmsRepo.ExerciseRepository;
import com.example.academicherald.repositories.lmsRepo.SubmittedExerciseRepository;
import org.springframework.stereotype.Service;

@Service
public class ExerciseService {

    private final ExerciseRepository exerciseRepository;
    private final SubmittedExerciseRepository submittedExerciseRepository;

    public ExerciseService(ExerciseRepository exerciseRepository, SubmittedExerciseRepository submittedExerciseRepository) {
        this.exerciseRepository = exerciseRepository;
        this.submittedExerciseRepository = submittedExerciseRepository;
    }

    public Exercise create (Exercise exercise){
        return exerciseRepository.save(exercise);
    }
//    public SubmittedExercise submitExercise(Exercise exerciseId, User studentId, String linkGitHubHw) {
//        SubmittedExercise submittedExercise = new SubmittedExercise();
//        submittedExercise.setExerciseId(exerciseId);
//        submittedExercise.setStudentId(studentId);
//        submittedExercise.setLinkGitHubHw(linkGitHubHw);
//
//        return submittedExerciseRepository.save(submittedExercise);
//    }

    public SubmittedExercise submittedExercise(SubmittedExercise submittedExercise){
        return submittedExerciseRepository.save(submittedExercise);
    }

}
