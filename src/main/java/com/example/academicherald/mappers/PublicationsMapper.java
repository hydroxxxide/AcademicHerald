package com.example.academicherald.mappers;

import com.example.academicherald.dto.PublicationsDTO;
import com.example.academicherald.models.Publications;
import org.modelmapper.ModelMapper;

public class PublicationsMapper {
    private final ModelMapper mapper;

    public PublicationsMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public PublicationsDTO convertToDTO(Publications publications){
        return mapper.map(publications, PublicationsDTO.class);
    }
}
