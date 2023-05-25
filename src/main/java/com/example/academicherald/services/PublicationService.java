package com.example.academicherald.services;

import com.example.academicherald.mappers.CategoryMapper;
import com.example.academicherald.mappers.UserMapper;
import com.example.academicherald.models.Category;
import com.example.academicherald.models.Publication;
import com.example.academicherald.models.User;
import com.example.academicherald.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserService userService;
    private final UserMapper userMapper;
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    public PublicationService(PublicationRepository publicationRepository, UserService userService, UserMapper userMapper, CategoryService categoryService, CategoryMapper categoryMapper) {
        this.publicationRepository = publicationRepository;
        this.userService = userService;
        this.userMapper = userMapper;

        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    public Publication create(Publication publication, Long userId, Long categoryId) {
        publication.setDateOfCreation(LocalDateTime.now());
        User author = userService.getById(userId);
        Category category = categoryService.getById(categoryId);
        publication.setAuthor(author);
        publication.setCategory(category);
        return publicationRepository.save(publication);
    }

    public Publication getById(Long id) {
        return publicationRepository.findByIdAndPassAndRdtIsNull(id, true);
    }

    public List<Publication> getAllAccepted() {
        return publicationRepository.getAllByPassAndRdtIsNull(true);
    }

    public List<Publication> getAllRejected() {
        return publicationRepository.getAllByPassAndRdtIsNull(false);
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
        Publication publication = getById(id);
        publication.setRdt(LocalDateTime.now());
        publicationRepository.save(publication);
    }

    public void confirmPublication(Long id, Boolean res){
        Publication publication = getById(id);
        publication.setPass(res);
        update(publication);
    }

}
