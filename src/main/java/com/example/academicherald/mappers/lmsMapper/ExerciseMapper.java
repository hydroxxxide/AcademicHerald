package com.example.academicherald.mappers.lmsMapper;

import com.example.academicherald.dto.lmsDto.ExerciseDto;
import com.example.academicherald.entity.lms.Exercise;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ExerciseMapper {
    private final ModelMapper mapper;

    public ExerciseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public ExerciseDto convertToDto(Exercise exercise) {
        return mapper.map(exercise, ExerciseDto.class);
    }

    public Exercise convertToEntity(ExerciseDto exerciseDto) {
        return mapper.map(exerciseDto, Exercise.class);
    }

    public List<ExerciseDto> convertToDTOList(List<Exercise> exercise) {
        List<ExerciseDto> exerciseDtoList = new ArrayList<>();
        for (Exercise e : exercise) {
            exerciseDtoList.add(convertToDto(e));
        }
        return exerciseDtoList;
    }
}
