package com.example.academicherald.dto.lmsDto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;
@Data
public class ChapterDto {
    private String name;
    @JsonIgnore
    private CourseDto course;

    private List<LecturesDto> lectures;
    private List<ExerciseDto> exercises;

}
