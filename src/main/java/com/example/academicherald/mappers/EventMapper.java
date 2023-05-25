package com.example.academicherald.mappers;

import com.example.academicherald.dto.EventDto;
import com.example.academicherald.models.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EventMapper {
    private final ModelMapper mapper;

    public EventMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }
    public EventDto convertToDto(Event event) {
        return mapper.map(event, EventDto.class);
    }

    public List<EventDto> convertToDTOList(List<Event> events) {
        List<EventDto> eventDtoList = new ArrayList<>();
        for (Event e : events) {
            eventDtoList.add(convertToDto(e));
        }
        return eventDtoList;
    }
}
