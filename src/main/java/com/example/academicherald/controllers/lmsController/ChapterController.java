package com.example.academicherald.controllers.lmsController;

import com.example.academicherald.dto.CommentDto;
import com.example.academicherald.dto.lmsDto.ChapterDto;
import com.example.academicherald.entity.Comment;
import com.example.academicherald.entity.lms.Chapter;
import com.example.academicherald.mappers.lmsMapper.ChapterMapper;
import com.example.academicherald.services.lmsService.ChapterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chapter")
public class ChapterController {
    private final ChapterService chapterService;
    private final ChapterMapper chapterMapper;

    public ChapterController(ChapterService chapterService, ChapterMapper chapterMapper) {
        this.chapterService = chapterService;
        this.chapterMapper = chapterMapper;
    }

    @PostMapping("/create")
    public ChapterDto createChapter(@RequestBody ChapterDto chapterDto,
                                    @RequestParam Long courseId) {
        Chapter chapter = chapterMapper.convertToEntity(chapterDto);
        Chapter createdChapter = chapterService.createChapter(chapter, courseId);
        return chapterMapper.convertToDto(createdChapter);
    }

    @PutMapping("/update")
    public ChapterDto updateChapter(@RequestBody Chapter chapter){
        return chapterMapper.convertToDto(chapterService.updateChapter(chapter));
    }

    @GetMapping("/getAllByChapter/{courseId}")
    public List<ChapterDto> getAllChapterByCourse(@PathVariable Long courseId){
        return chapterMapper.convertToDTOList(chapterService.getAllChapterByCourse(courseId));

    }

}
