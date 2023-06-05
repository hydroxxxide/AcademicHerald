package com.example.academicherald.repositories;

import com.example.academicherald.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
    Like findByPublicationIdAndUserId(Long publicationId, Long userId);
}
