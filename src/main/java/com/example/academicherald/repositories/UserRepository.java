package com.example.academicherald.repositories;

import com.example.academicherald.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUsername(String username);
    User findByResetToken(String token);

    Optional<User> findByIdAndRdtIsNull(Long id);

    List<User> findAllByRdtIsNull();
}
