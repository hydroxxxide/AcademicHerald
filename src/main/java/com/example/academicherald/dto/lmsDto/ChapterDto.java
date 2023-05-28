package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.models.lms.Course;
import com.example.academicherald.models.lms.Exercise;
import com.example.academicherald.models.lms.Lectures;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
public class ChapterDto {
    private String name;
    private Course course;

}
