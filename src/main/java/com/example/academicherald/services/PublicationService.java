package com.example.academicherald.services;

import com.example.academicherald.models.Publication;
import com.example.academicherald.models.User;
import com.example.academicherald.repositories.PublicationRepository;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PublicationService {
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    public PublicationService(PublicationRepository publicationRepository, UserRepository userRepository) {
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    public Publication create(Publication publication, Long user_id) {
        publication.setDateOfCreation(LocalDateTime.now());
        User author = userRepository.findById(user_id).orElse(null);
        publication.setAuthor(author);
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
