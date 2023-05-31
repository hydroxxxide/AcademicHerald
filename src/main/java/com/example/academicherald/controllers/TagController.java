package com.example.academicherald.controllers;

import com.example.academicherald.dto.TagDto;
import com.example.academicherald.mappers.TagMapper;
import com.example.academicherald.entity.Tag;
import com.example.academicherald.services.TagService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @PostMapping("/create")
    public TagDto create(@RequestBody Tag tag){
        return tagMapper.convertToDTO(tagService.create(tag));
    }
    @GetMapping("/getById/{id}")
    public TagDto getById(@PathVariable Long id){
        return tagMapper.convertToDTO(tagService.getById(id));
    }
    @GetMapping("/getAllTags")
    public List<TagDto> allTags(){
        return tagMapper.convertToDTOList(tagService.getAllTag());
    }
    @PutMapping("/update")
    public TagDto update(@RequestBody Tag tag){
        return tagMapper.convertToDTO(tagService.update(tag));
    }
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id){
        tagService.delete(id);
    }




}
