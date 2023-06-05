package com.example.academicherald.repositories;

import com.example.academicherald.entity.Comment;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPublication(Publication publication);
    List<Comment> findByUser(User user);

    Comment findByIdAndRdtIsNull(Long id);

    List<Comment> findByPublicationAndRdtIsNull(Publication publication);

    List<Comment> findByUserAndRdtIsNull(User user);
}
