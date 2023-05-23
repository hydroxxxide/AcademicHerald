package com.example.academicherald.repositories;

import com.example.academicherald.models.Publication;
import com.example.academicherald.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PublicationRepository extends JpaRepository<Publication, Long> {
//    List<Publication> findByTagId(Long tagId);

}
