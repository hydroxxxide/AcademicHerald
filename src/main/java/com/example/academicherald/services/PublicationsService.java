package com.example.academicherald.services;

import com.example.academicherald.models.Publications;
import com.example.academicherald.models.User;
import com.example.academicherald.repositories.PublicationsRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicationsService {
    public final PublicationsRepository publicationsRepository;

    public PublicationsService(PublicationsRepository publicationsRepository) {
        this.publicationsRepository = publicationsRepository;
    }
    public Publications createPublications(Publications publications){
        return publicationsRepository.save(publications);
    }
    public Publications getById(Long id){
        return publicationsRepository.findById(id).orElse(null);
    }
    public void deleteById(Long id){
            publicationsRepository.deleteById(id);
    }
}
