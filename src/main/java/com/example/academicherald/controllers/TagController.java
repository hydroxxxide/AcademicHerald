package com.example.academicherald.controllers;

import com.example.academicherald.dto.TagDto;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.entity.Tag;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.TagMapper;
import com.example.academicherald.services.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Slf4j
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @PostMapping("/create")
    public ResponseMessage<TagDto> create(@RequestBody TagDto tagDto) {
        Tag tag = tagMapper.convertToEntity(tagDto);
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTO(tagService.create(tag)),
                    ResultCode.SUCCESS,
                    "Тег успешно создан",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: create", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/getById/{id}")
    public ResponseMessage<TagDto> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTO(tagService.getById(id)),
                    ResultCode.SUCCESS,
                    "Тег успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: getById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/getAllTags")
    public ResponseMessage<List<TagDto>> allTags() {
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTOList(tagService.getAllTag()),
                    ResultCode.SUCCESS,
                    "Список тегов",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: allTags", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/update")
    public ResponseMessage<TagDto> update(@RequestBody Tag tag) {
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTO(tagService.update(tag)),
                    ResultCode.SUCCESS,
                    "Тег успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: update", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<String> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    tagService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: delete", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
