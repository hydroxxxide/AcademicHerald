package com.example.academicherald.dto.lmsDto;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.enums.CourseType;
import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Chapter;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CourseDto {
    @Enumerated(EnumType.STRING)
    private CourseType type;
    @DateTimeFormat(pattern = "dd-MM-yyyy")

    private LocalDateTime startCourse = LocalDateTime.now();
    private UserDto mentor;
    private List<UserDto> students;
    private List<ChapterDto> chapters;

}
