package com.example.academicherald.controllers;

import com.example.academicherald.dto.PublicationDto;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.entity.ResponseMessage;
import com.example.academicherald.enums.ResultCode;
import com.example.academicherald.mappers.PublicationMapper;
import com.example.academicherald.services.PublicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/publication")
@CrossOrigin(origins = "http://127.0.0.1:5500")

public class PublicationController {
    private final PublicationService publicationService;
    private final PublicationMapper mapper;


    public PublicationController(PublicationService publicationService, PublicationMapper mapper) {
        this.publicationService = publicationService;
        this.mapper = mapper;
    }


    @PostMapping("/create")
    public ResponseMessage<PublicationDto> createPublication(
            @RequestBody PublicationDto publicationDto,
            @RequestParam Long userId,
            @RequestParam Long categoryId,
            @RequestParam Long[] tagsIds
    ) {
        Publication publication = mapper.convertToEntity(publicationDto);
        try {
            return new ResponseMessage<>(
                    mapper.convertToDto(publicationService.create(publication, userId, categoryId, tagsIds)),
                    ResultCode.SUCCESS,
                    "Публикация успешно создана",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: createPublication", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseMessage<PublicationDto> getById(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    mapper.convertToDto(publicationService.getById(id)),
                    ResultCode.SUCCESS,
                    "Публикация успешно найдена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: getById", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/get/all")
    public List<PublicationDto> getAll() {
        return mapper.convertToDTOList(publicationService.getAllAccepted());
    }

    @PutMapping("/update")
    public ResponseMessage<PublicationDto> update(@RequestBody Publication publication,
                                                  @RequestParam Long userId,
                                                  @RequestParam Long categoryId) throws Exception {
        try {
            return new ResponseMessage<>(
                    mapper.convertToDto(publicationService.update(publication, userId, categoryId)),
                    ResultCode.SUCCESS,
                    "Публикация успешно обновлена",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: update", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseMessage<String> delete(@PathVariable Long id) {
        try {
            return new ResponseMessage<>(
                    publicationService.delete(id),
                    ResultCode.SUCCESS,
                    "Успешное удаление",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: delete", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    //Вытаскиваем список публикаций по id тега
    @GetMapping("/listByTag/{tagId}")
    public ResponseMessage<List<PublicationDto>> listOfPublicationsByTag(@PathVariable Long tagId) {
        try {
            return new ResponseMessage<>(
                    mapper.convertToDTOList(publicationService.getPublicationsByTagId(tagId)),
                    ResultCode.SUCCESS,
                    "Список публикаций по тегу успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: listOfPublicationsByTag", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    //Вытаскиваем список публикаций по id категории
    @GetMapping("/getByCategory/{categoryId}")
    public ResponseMessage<List<PublicationDto>> getPublicationsByCategoryId(@PathVariable Long categoryId) {
        try {
            return new ResponseMessage<>(
                    mapper.convertToDTOList(publicationService.getPublicationsByCategoryId(categoryId)),
                    ResultCode.SUCCESS,
                    "Список публикаций по категории успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: getPublicationsByCategoryId", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    //Вытаскиваем список публикаций по id автора(какие посты он выложил)
    @GetMapping("/user/{authorId}")
    public ResponseMessage<List<PublicationDto>> getPublicationsByUser(@PathVariable Long authorId) {
        try {
            return new ResponseMessage<>(
                    mapper.convertToDTOList(publicationService.getPublicationsByAuthorId(authorId)),
                    ResultCode.SUCCESS,
                    "Список публикаций по автору успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: getPublicationsByUser", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }

    @GetMapping("/popular")
    public ResponseMessage<List<PublicationDto>> getPopularPublicationList() {
        try {
            return new ResponseMessage<>(
                    mapper.convertToDTOList(publicationService.sortByPopularity()),
                    ResultCode.SUCCESS,
                    "Список публикаций по популярности успешно найден",
                    ResultCode.SUCCESS.getHttpCode());
        } catch (Exception e) {
            log.error("PublicationController: getPopular", e);
            return new ResponseMessage<>(null, ResultCode.FAIL, e.getMessage(), ResultCode.FAIL.getHttpCode());
        }
    }
}
