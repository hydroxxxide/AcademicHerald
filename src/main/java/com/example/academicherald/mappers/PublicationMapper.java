package com.example.academicherald.mappers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.models.Publication;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PublicationMapper {
    private final ModelMapper mapper;

    public PublicationMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public PublicationDto convertToDto(Publication publication) {
        return mapper.map(publication, PublicationDto.class);
    }
    public Publication convertToEntity(PublicationDto publicationDto) {
        return mapper.map(publicationDto, Publication.class);
    }
    public List<PublicationDto> convertToDTOList(List<Publication> publications) {
        List<PublicationDto> publicationDtoList = new ArrayList<>();
        for (Publication p : publications) {
            publicationDtoList.add(convertToDto(p));
        }
        return publicationDtoList;
    }

}
