package com.example.academicherald.services;

import com.example.academicherald.models.Tag;
import com.example.academicherald.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    public List<Tag> getAllTag() {
        return tagRepository.findAllByRdtIsNull();
    }

    public Tag getById(Long id) {
        return tagRepository.findByIdAndRdtIsNull(id);
    }

    public Tag update(Tag newTag) {
        Tag oldTag = getById(newTag.getId());
        oldTag.setName(newTag.getName());
        return tagRepository.save(oldTag);
    }

    public void delete(Long id) {
        Tag tag = getById(id);
        tag.setRdt(LocalDateTime.now());
        tagRepository.save(tag);
    }
}
