package com.example.academicherald.controllers;

import com.example.academicherald.dto.EventDto;
import com.example.academicherald.entity.Event;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.EventMapper;
import com.example.academicherald.services.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Slf4j
public class EventController {
    private final EventService eventService;
    private final EventMapper eventMapper;

    public EventController(EventService eventService, EventMapper eventMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
    }


    @PostMapping("/create")
    public ResponseMessage<EventDto> create(@RequestBody EventDto eventDto,
                                            @RequestParam Long userId) {
        Event event = eventMapper.convertToEntity(eventDto);
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDto(eventService.create(event, userId)),
                    ResultCode.SUCCESS,
                    "Ивент успешно создан",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("EventController: create", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseMessage<EventDto> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDto(eventService.getById(id)),
                    ResultCode.SUCCESS,
                    "Ивент успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("EventController: getById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/get/all")
    public ResponseMessage<List<EventDto>> getAll() {
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDTOList(eventService.getAll()),
                    ResultCode.SUCCESS,
                    "Список ивентов",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("EventController: getAll", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/update")
    public ResponseMessage<EventDto> update(@RequestBody Event event) {
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDto(eventService.update(event)),
                    ResultCode.SUCCESS,
                    "Ивент успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("EventController: update", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<String> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    eventService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("EventController: delete", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

}
