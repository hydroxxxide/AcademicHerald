package com.example.academicherald.repositories;

import com.example.academicherald.models.Category;
import com.example.academicherald.models.Comment;
import com.example.academicherald.models.Publication;
import com.example.academicherald.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
//    //    List<Publication> findByTagId(Long tagId);
//    List<Publication> findByCategory(Category category);

    List<Publication> findByCategory(Category category);

}
