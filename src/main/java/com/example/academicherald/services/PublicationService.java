package com.example.academicherald.services;

import com.example.academicherald.models.Publication;
import com.example.academicherald.repositories.PublicationRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicationService {
    private final PublicationRepository repository;

    public PublicationService(PublicationRepository repository) {
        this.repository = repository;
    }

    public Publication create(Publication publication){
        return repository.save(publication);
    }

    public Publication getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public Publication update(Publication newPublication){
        Publication oldPublication = getById(newPublication.getId());
        oldPublication.setTitle(newPublication.getTitle());
        oldPublication.setSubtitle(newPublication.getSubtitle());
        oldPublication.setText(newPublication.getText());
        oldPublication.setDateOfCreation(newPublication.getDateOfCreation());
        oldPublication.setCategory(newPublication.getCategory());
        oldPublication.setAuthor(newPublication.getAuthor());
        oldPublication.setType(newPublication.getType());
        return repository.save(oldPublication);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
