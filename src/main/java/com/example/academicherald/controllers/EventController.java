package com.example.academicherald.controllers;

import com.example.academicherald.dto.EventDto;
import com.example.academicherald.mappers.EventMapper;
import com.example.academicherald.entity.Event;
import com.example.academicherald.services.EventService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;
    private final EventMapper mapper;

    public EventController(EventService eventService, EventMapper mapper) {
        this.eventService = eventService;
        this.mapper = mapper;
    }


    @PostMapping("/create")
    public EventDto create(@RequestBody Event event,
                                 @RequestParam Long userId) {
        return mapper.convertToDto(eventService.create(event, userId));
    }

    @GetMapping("/get/{id}")
    public EventDto getById(@PathVariable Long id) {
        return mapper.convertToDto(eventService.getById(id));
    }

    @GetMapping("/get/all")
    public List<EventDto> getAll() {
        return mapper.convertToDTOList(eventService.getAll());
    }

    @PutMapping("/update")
    public EventDto update(@RequestBody Event event) {
        return mapper.convertToDto(eventService.update(event));
    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
        eventService.delete(id);
    }

}
