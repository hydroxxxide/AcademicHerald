package com.example.academicherald.services;

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
    private final CategoryService categoryService;

    public PublicationService(PublicationRepository publicationRepository, UserService userService, CategoryService categoryService) {
        this.publicationRepository = publicationRepository;
        this.userService = userService;
        this.categoryService = categoryService;
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

    public Publication update(Publication newPublication, Long userId) throws Exception{
        Publication oldPublication = getById(newPublication.getId());
        User author = userService.getById(userId);
        if (author.equals(oldPublication.getAuthor())){
            oldPublication.setTitle(newPublication.getTitle());
            oldPublication.setSubtitle(newPublication.getSubtitle());
            oldPublication.setText(newPublication.getText());
            oldPublication.setDateOfCreation(newPublication.getDateOfCreation());
            oldPublication.setCategory(newPublication.getCategory());
            oldPublication.setType(newPublication.getType());
            return publicationRepository.save(oldPublication);
        }
        else throw new Exception("Пользователь " + oldPublication.getAuthor().getUsername() +
                " не является автором данной публикации");
    }

    public void delete(Long id) {
        Publication publication = getById(id);
        publication.setRdt(LocalDateTime.now());
        publicationRepository.save(publication);
    }

    public void confirmPublication(Long id, Boolean res){
        Publication publication = publicationRepository.findById(id).orElse(null);
        publication.setPass(res);
        publicationRepository.save(publication);
    }

}
