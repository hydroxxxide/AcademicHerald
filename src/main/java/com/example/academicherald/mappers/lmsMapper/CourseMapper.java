package com.example.academicherald.mappers.lmsMapper;

import com.example.academicherald.dto.lmsDto.CourseDto;
import com.example.academicherald.entity.lms.Course;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseMapper {
    private final ModelMapper mapper;

    public CourseMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public CourseDto convertToDto(Course course) {
        return mapper.map(course, CourseDto.class);
    }

    public List<CourseDto> convertToDTOList(List<Course> courses) {
        List<CourseDto> courseDtoList = new ArrayList<>();
        for (Course c : courses) {
            courseDtoList.add(convertToDto(c));
        }
        return courseDtoList;
    }

    public Course convertToEntity(CourseDto courseDto) {
        return mapper.map(courseDto, Course.class);
    }

}
