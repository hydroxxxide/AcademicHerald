package com.example.academicherald.repositories;

import com.example.academicherald.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findAllByRdtIsNull();

    Tag findByIdAndRdtIsNull(Long id);
}
