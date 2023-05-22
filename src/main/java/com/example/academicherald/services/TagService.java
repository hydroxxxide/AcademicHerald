package com.example.academicherald.services;

import com.example.academicherald.models.Tag;
import com.example.academicherald.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag create(Tag tag){
        return tagRepository.save(tag);
    }
    public List<Tag> getAllTag(){
        return tagRepository.findAll();
    }
    public Tag getById(Long id){
        return tagRepository.findById(id).orElse(null);
    }
    public Tag update(Tag newTag){
        Tag oldTag = tagRepository.getById(newTag.getId());
        oldTag.setName(newTag.getName());
        return tagRepository.save(oldTag);
    }
    public List<Tag> getByIds(List<Long> tagIds) {
        List<Tag> tags = tagRepository.findAllById(tagIds);
        return tags;
    }
    public void delete(Long id){
        tagRepository.deleteById(id);
    }
}
