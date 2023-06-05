package com.example.academicherald.repositories;

import com.example.academicherald.entity.User;
import com.example.academicherald.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByUsername(String username);

    User findByResetToken(String token);

    User findByIdAndRdtIsNull(Long id);

    List<User> findAllByRdtIsNull();

    User findByIdAndRole(Long id, UserRole role);
    @Query("SELECT u FROM User u WHERE u.id IN :id AND u.role = :role")
    List<User> findAllByIdAndRole(List<Long> id, UserRole role);



    @Query(value = "select email from users", nativeQuery = true)
    List<String> findAllEmails();

}
