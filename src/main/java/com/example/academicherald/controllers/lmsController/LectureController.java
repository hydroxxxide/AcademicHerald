package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.lmsDto.LecturesDto;
import com.example.academicherald.entity.lms.Lectures;
import com.example.academicherald.mappers.lmsMapper.LecturesMapper;
import com.example.academicherald.services.lmsService.LecturesService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lecture")
public class LectureController {
    private final LecturesService lecturesService;
    private final LecturesMapper lecturesMapper;

    public LectureController(LecturesService lecturesService, LecturesMapper lecturesMapper) {
        this.lecturesService = lecturesService;
        this.lecturesMapper = lecturesMapper;
    }
    @PostMapping("/create")
    public LecturesDto createLecture(@RequestBody LecturesDto lecturesDto,
                                     @RequestParam Long chapterId) {
        Lectures lectures = lecturesMapper.convertToEntity(lecturesDto);
        Lectures createdLectures = lecturesService.createLecture(lectures, chapterId);
        return lecturesMapper.convertToDto(createdLectures);
    }
    @GetMapping("/get/{id}")
    public LecturesDto getById(@PathVariable Long id) {
        return lecturesMapper.convertToDto(lecturesService.getById(id));
    }
}
