package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.models.lms.Course;

import lombok.Data;

import java.util.List;
@Data
public class ChapterDto {
    private String name;
    private Course course;

}
