package com.example.academicherald.repositories;

import com.example.academicherald.entity.Publication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> getAllByPassAndRdtIsNull(Boolean pass);

    Publication findByIdAndPassAndRdtIsNull(Long id, Boolean pass);
}
