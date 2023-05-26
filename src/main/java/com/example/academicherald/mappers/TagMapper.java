package com.example.academicherald.mappers;

import com.example.academicherald.dto.TagDto;
import com.example.academicherald.models.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TagMapper {
    private final ModelMapper mapper;

    public TagMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }


    public List<TagDto> convertToDTOList(List<Tag> tags) {
        List<TagDto> tagDtoList = new ArrayList<>();
        for (Tag t : tags) {
            tagDtoList.add(convertToDTO(t));
        }
        return tagDtoList;
    }

    public TagDto convertToDTO(Tag tag) {
        return mapper.map(tag, TagDto.class);
    }
}
