package com.example.academicherald.controllers;

import com.example.academicherald.dto.CategoryDto;
import com.example.academicherald.dto.EventDto;
import com.example.academicherald.dto.TagDto;
import com.example.academicherald.entity.Category;
import com.example.academicherald.entity.Event;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.entity.Tag;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.CategoryMapper;
import com.example.academicherald.mappers.EventMapper;
import com.example.academicherald.mappers.TagMapper;
import com.example.academicherald.services.CategoryService;
import com.example.academicherald.services.EventService;
import com.example.academicherald.services.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/manager")
@CrossOrigin(origins = "http://127.0.0.1:5500")
@Slf4j
public class ManagerController {
    private final EventService eventService;
    private final EventMapper eventMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;
    private final TagService tagService;
    private final TagMapper tagMapper;

    public ManagerController(EventService eventService, EventMapper eventMapper, CategoryService categoryService, CategoryMapper categoryMapper, TagService tagService, TagMapper tagMapper) {
        this.eventService = eventService;
        this.eventMapper = eventMapper;
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }


    // управление над ивентами
    @PostMapping("/event/create")
    public ResponseMessage<EventDto> createEvent(@RequestBody EventDto eventDto,
                                                 @RequestParam Long userId) {
        Event event = eventMapper.convertToEntity(eventDto);
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDto(eventService.create(event, userId)),
                    ResultCode.SUCCESS,
                    "Ивент успешно создан",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: createEvent", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/event/get/{id}")
    public ResponseMessage<EventDto> getEventById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDto(eventService.getById(id)),
                    ResultCode.SUCCESS,
                    "Ивент успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: getById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/event/get/all")
    public ResponseMessage<List<EventDto>> getAllEvents() {
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDTOList(eventService.getAll()),
                    ResultCode.SUCCESS,
                    "Список ивентов",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: getAllEvents", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/event/update")
    public ResponseMessage<EventDto> updateEvent(@RequestBody Event event) {
        try {
            return new ResponseMessage<>(
                    eventMapper.convertToDto(eventService.update(event)),
                    ResultCode.SUCCESS,
                    "Ивент успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: updateEvent", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/event/delete/{id}")
    public ResponseMessage<String> deleteEvent(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    eventService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: deleteEvent", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PostMapping("/category/create")
    public ResponseMessage<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto) {
        Category category = categoryMapper.convertToEntity(categoryDto);
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDto(categoryService.create(category)),
                    ResultCode.SUCCESS,
                    "Категория успешно создана",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: createCategory", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/category/get/{id}")
    public ResponseMessage<CategoryDto> getCategoryById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDto(categoryService.getById(id)),
                    ResultCode.SUCCESS,
                    "Категория успешно найдена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: getCategoryById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/category/get/all")
    public ResponseMessage<List<CategoryDto>> getAllCategories() {
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDTOList(categoryService.getAll()),
                    ResultCode.SUCCESS,
                    "Список всех категорий",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: getAllCategories", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/category/update")
    public ResponseMessage<CategoryDto> updateCategory(@RequestBody Category category) {
        try {
            return new ResponseMessage<>(
                    categoryMapper.convertToDto(categoryService.update(category)),
                    ResultCode.SUCCESS,
                    "Категория успешно обновлена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: updateCategory", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/category/delete/{id}")
    public ResponseMessage<String> deleteCategory(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    categoryService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("ManagerController: deleteCategory", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }


    // управление тегами
    @PostMapping("/tag/create")
    public ResponseMessage<TagDto> createTag(@RequestBody TagDto tagDto) {
        Tag tag = tagMapper.convertToEntity(tagDto);
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTO(tagService.create(tag)),
                    ResultCode.SUCCESS,
                    "Тег успешно создан",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: createTag", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/tag/get/{id}")
    public ResponseMessage<TagDto> getTagById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTO(tagService.getById(id)),
                    ResultCode.SUCCESS,
                    "Тег успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: getTagById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/tag/getAllTags")
    public ResponseMessage<List<TagDto>> getAllTags() {
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTOList(tagService.getAllTag()),
                    ResultCode.SUCCESS,
                    "Список тегов",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: getAllTags", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @PutMapping("/tag/update")
    public ResponseMessage<TagDto> updateTag(@RequestBody Tag tag) {
        try {
            return new ResponseMessage<>(
                    tagMapper.convertToDTO(tagService.update(tag)),
                    ResultCode.SUCCESS,
                    "Тег успешно обновлен",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: updateTag", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/tag/delete/{id}")
    public ResponseMessage<String> deleteTag(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    tagService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("TagController: deleteTag", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
