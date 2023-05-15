package com.example.academicherald.mappers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.models.Publication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PublicationMapper {
    private final ModelMapper mapper;

    public PublicationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PublicationDto convertToDto(Publication publication){
        return mapper.map(publication, PublicationDto.class);
    }
}
