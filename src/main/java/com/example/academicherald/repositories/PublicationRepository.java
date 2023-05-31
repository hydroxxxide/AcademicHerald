package com.example.academicherald.repositories;

import com.example.academicherald.models.Category;
import com.example.academicherald.models.Publication;
import com.example.academicherald.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
    List<Publication> getAllByPassAndRdtIsNull(Boolean pass);

    Publication findByIdAndPassAndRdtIsNull(Long id, Boolean pass);
    List<Publication> findByTagsId(Long tagId);

    List<Publication> findByCategory(Category category);

    List<Publication> findByAuthor(User author);
}
