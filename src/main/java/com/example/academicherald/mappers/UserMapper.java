package com.example.academicherald.mappers;

import com.example.academicherald.dto.UserDto;
import com.example.academicherald.dto.lmsDto.ExerciseDto;
import com.example.academicherald.models.User;
import com.example.academicherald.models.lms.Exercise;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserMapper {
    private final ModelMapper mapper;

    public UserMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public List<UserDto> convertToDTOList(List<User> users) {
        List<UserDto> userDtoList = new ArrayList<>();
        for (User u : users) {
            userDtoList.add(convertToDTO(u));
        }
        return userDtoList;
    }
    public User convertToEntity(UserDto userDto) {
        return mapper.map(userDto, User.class);
    }

    public UserDto convertToDTO(User user) {
        return mapper.map(user, UserDto.class);
    }
}
