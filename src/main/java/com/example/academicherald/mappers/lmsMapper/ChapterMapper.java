package com.example.academicherald.mappers.lmsMapper;

import com.example.academicherald.dto.lmsDto.ChapterDto;
import com.example.academicherald.entity.lms.Chapter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ChapterMapper {
    private final ModelMapper mapper;

    public ChapterMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public ChapterDto convertToDto(Chapter chapter) {
        return mapper.map(chapter, ChapterDto.class);
    }

    public List<ChapterDto> convertToDTOList(List<Chapter> chapters) {
        List<ChapterDto> chapterDtoList = new ArrayList<>();
        for (Chapter c : chapters) {
            chapterDtoList.add(convertToDto(c));
        }
        return chapterDtoList;
    }
    public Chapter convertToEntity(ChapterDto chapterDto) {
        return mapper.map(chapterDto, Chapter.class);
    }
}
