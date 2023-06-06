package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.LecturesDto;
import com.example.academicherald.dto.lmsDto.SubmittedExerciseDto;
import com.example.academicherald.entity.lms.Lectures;
import com.example.academicherald.entity.lms.SubmittedExercise;
import com.example.academicherald.mappers.lmsMapper.SubmittedExerciseMapper;
import com.example.academicherald.services.lmsService.SubmittedExerciseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/homework")
public class SubmittedExerciseController {
    private final SubmittedExerciseMapper submittedExerciseMapper;
    private final SubmittedExerciseService submittedExerciseService;

    public SubmittedExerciseController(SubmittedExerciseMapper submittedExerciseMapper, SubmittedExerciseService submittedExerciseService) {
        this.submittedExerciseMapper = submittedExerciseMapper;
        this.submittedExerciseService = submittedExerciseService;
    }

//    @GetMapping("/getAllByChapter/{chapterId}")
//    public List<SubmittedExerciseDto> findByChapter(@PathVariable Long chapterId){
//        return submittedExerciseMapper.convertToDTOList(submittedExerciseService.findByChapter(chapterId));
//    }

//    @PutMapping("/update")
//    public SubmittedExerciseDto update(@RequestBody SubmittedExercise submittedExercise){
//        return submittedExerciseMapper.convertToDto(submittedExerciseService.updateSubmittedExercise(submittedExercise));
//    }
}
