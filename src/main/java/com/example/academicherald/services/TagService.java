package com.example.academicherald.services;

import com.example.academicherald.models.Publication;
import com.example.academicherald.models.Tag;
import com.example.academicherald.repositories.PublicationRepository;
import com.example.academicherald.repositories.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {
    private final TagRepository tagRepository;
    private final PublicationRepository publicationRepository;
    public TagService(TagRepository tagRepository, PublicationRepository publicationRepository) {
        this.tagRepository = tagRepository;
        this.publicationRepository = publicationRepository;
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

    public void delete(Long id){
        tagRepository.deleteById(id);
    }
}
