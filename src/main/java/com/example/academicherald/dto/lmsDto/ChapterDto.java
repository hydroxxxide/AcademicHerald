package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.models.lms.Course;

import com.example.academicherald.models.lms.Exercise;
import com.example.academicherald.models.lms.Lectures;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.OneToMany;
import java.util.List;
@Data
public class ChapterDto {
    private String name;
    @JsonIgnore
    private CourseDto course;

    private List<LecturesDto> lectures;
    private List<ExerciseDto> exercises;

}
