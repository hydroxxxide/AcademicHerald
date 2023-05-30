package com.example.academicherald.repositories;

import com.example.academicherald.enums.UserRole;
import com.example.academicherald.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    @Query("SELECT u FROM User u WHERE u.role = :role")
    List<User> findAllByRole(@Param("role") UserRole role);


    User findByIdAndRole(Long id, UserRole role);
    @Query("SELECT u FROM User u WHERE u.id IN :id AND u.role = :role")
    List<User> findAllByIdAndRole(List<Long> id, UserRole role);


}
