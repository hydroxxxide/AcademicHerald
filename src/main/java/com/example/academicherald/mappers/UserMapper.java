package com.example.academicherald.mappers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public UserDto convertToDTO(User user){
        return mapper.map(user, UserDto.class);
    }
}
