package com.example.academicherald.services;

import com.example.academicherald.models.Category;
import com.example.academicherald.models.Publication;
import com.example.academicherald.models.Tag;
import com.example.academicherald.models.User;
import com.example.academicherald.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final TagService tagService;

    public PublicationService(PublicationRepository publicationRepository, UserService userService, CategoryService categoryService, TagService tagService) {
        this.publicationRepository = publicationRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.tagService = tagService;
    }

    //    public Publication create(Publication publication, Long userId, Long categoryId) {
//        publication.setDateOfCreation(LocalDateTime.now());
//        User author = userService.getById(userId);
//        Category category = categoryService.getById(categoryId);
//        publication.setAuthor(author);
//        publication.setCategory(category);
//        return publicationRepository.save(publication);
//    }
    public Publication createPublication(Publication publication, Long userId, Long categoryId) {
        publication.setDateOfCreation(LocalDateTime.now());
        User author = userService.getById(userId);
        Category category = categoryService.getById(categoryId);
        List<Tag> tags = publication.getTags();
        publication.setAuthor(author);
        publication.setCategory(category);
        publication.setTags(tags);
        return publicationRepository.save(publication);
    }

    public Publication getById(Long id) {
        return publicationRepository.findById(id).orElse(null);
    }

    public List<Publication> getAll() {
        return publicationRepository.findAll();
    }

    public Publication update(Publication newPublication) {
        Publication oldPublication = getById(newPublication.getId());
        oldPublication.setTitle(newPublication.getTitle());
        oldPublication.setSubtitle(newPublication.getSubtitle());
        oldPublication.setText(newPublication.getText());
        oldPublication.setDateOfCreation(newPublication.getDateOfCreation());
        oldPublication.setCategory(newPublication.getCategory());
        oldPublication.setAuthor(newPublication.getAuthor());
        oldPublication.setType(newPublication.getType());
        return publicationRepository.save(oldPublication);
    }

    public void delete(Long id) {
        publicationRepository.deleteById(id);
    }
}
