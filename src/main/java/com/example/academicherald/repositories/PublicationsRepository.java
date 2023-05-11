package com.example.academicherald.repositories;

import com.example.academicherald.models.Publications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublicationsRepository extends JpaRepository<Publications, Long> {
}
