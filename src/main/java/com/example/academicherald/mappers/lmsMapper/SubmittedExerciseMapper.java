package com.example.academicherald.mappers.lmsMapper;

import com.example.academicherald.dto.lmsDto.SubmittedExerciseDto;
import com.example.academicherald.entity.lms.SubmittedExercise;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SubmittedExerciseMapper {
    private final ModelMapper mapper;

    public SubmittedExerciseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public SubmittedExerciseDto convertToDto(SubmittedExercise submittedExercise) {
        return mapper.map(submittedExercise, SubmittedExerciseDto.class);
    }
    public SubmittedExercise convertToEntity(SubmittedExerciseDto submittedExerciseDto) {
        return mapper.map(submittedExerciseDto, SubmittedExercise.class);
    }
    public List<SubmittedExerciseDto> convertToDTOList(List<SubmittedExercise> submittedExercises) {
        List<SubmittedExerciseDto> submittedExerciseDtoList = new ArrayList<>();
        for (SubmittedExercise s : submittedExercises) {
            submittedExerciseDtoList.add(convertToDto(s));
        }
        return submittedExerciseDtoList;
    }
}
