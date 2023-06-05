package com.example.academicherald.mappers.lmsMapper;

import com.example.academicherald.dto.lmsDto.LecturesDto;
import com.example.academicherald.entity.lms.Lectures;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LecturesMapper {
    private final ModelMapper mapper;

    public LecturesMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public LecturesDto convertToDto(Lectures lectures) {
        return mapper.map(lectures, LecturesDto.class);
    }

    public Lectures convertToEntity(LecturesDto lecturesDto) {
        return mapper.map(lecturesDto, Lectures.class);
    }

    public List<LecturesDto> convertToDTOList(List<Lectures> lectures) {
        List<LecturesDto> lecturesDtoList = new ArrayList<>();
        for (Lectures c : lectures) {
            lecturesDtoList.add(convertToDto(c));
        }
        return lecturesDtoList;
    }
}
