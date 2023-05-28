package com.example.academicherald.services;

import com.example.academicherald.models.*;
import com.example.academicherald.repositories.PublicationRepository;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final TagService tagService;
    private final UserRepository userRepository;


    public PublicationService(PublicationRepository publicationRepository, UserService userService, CategoryService categoryService, TagService tagService, UserRepository userRepository
    ) {
        this.publicationRepository = publicationRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.tagService = tagService;
        this.userRepository = userRepository;
    }

    public Publication createPublication(Publication publication, Long userId, List<Category> categories, Long[] tagIds) {
        publication.setDateOfCreation(LocalDateTime.now());
        User author = userService.getById(userId);

        List<Tag> tags = new ArrayList<>();
        for (Long tagId : tagIds) {
            Tag tag = tagService.getById(tagId);
            tags.add(tag);
        }

        publication.setAuthor(author);
        if (author != null) {
            author.getPublications().add(publication); // добавляем публикацию в список публикаций пользователя
        }
        if (categories != null) {
            for (Category category : categories) {
                category.getPublications().add(publication); // добавляем публикацию в список публикаций категории
            }
        }
        if (tags != null) {
            for (Tag tag : tags) {
                tag.getPublications().add(publication); // добавляем публикацию в список публикаций тега
            }
        }
        publication.setCategory(categories);
        publication.setTags(tags);

        return publicationRepository.save(publication);
    }


    //Вытаскиваем список публикаций по id тега
    public List<Publication> getPublicationsByTagId(Long tagId) {
        return publicationRepository.findByTagsId(tagId);
    }
    //Вытаскиваем список публикаций по id категории
    public List<Publication> getPublicationsByCategoryId(Long categoryId) {
        Category category = categoryService.getById(categoryId);
        return publicationRepository.findByCategory(category);
    }
    //Вытаскиваем список публикаций по id автора(какие посты он выложил)
    public List<Publication> getPublicationsByAuthorId(Long authorId) {
        User author = userRepository.findById(authorId).orElse(null);
        if (author != null) {
            return publicationRepository.findByAuthor(author);
        }
        return Collections.emptyList();
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
