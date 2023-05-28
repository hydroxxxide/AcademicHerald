package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.enums.CourseType;
import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Chapter;
import com.example.academicherald.models.lms.Lectures;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDto {
    private CourseType type;
    private LocalDateTime startCourse = LocalDateTime.now();
    private User mentor;
    private List<User> students;
    private List<Chapter> chapters;

}
