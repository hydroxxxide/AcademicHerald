package com.example.academicherald.repositories.lmsRepo;

import com.example.academicherald.entity.lms.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
}
