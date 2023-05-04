package com.example.academicherald.mappers;

import com.example.academicherald.dto.UserDTO;
import com.example.academicherald.models.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public UserDTO convertToDTO(User user){
        return mapper.map(user, UserDTO.class);
    }
}
