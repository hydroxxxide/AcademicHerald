package com.example.academicherald.repositories;

import com.example.academicherald.models.Comment;
import com.example.academicherald.models.Publication;
import com.example.academicherald.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPublication(Publication publication);
    List<Comment> findByUser(User user);

}
