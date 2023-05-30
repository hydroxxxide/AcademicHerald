package com.example.academicherald.services;

import com.example.academicherald.models.Comment;
import com.example.academicherald.models.Publication;
import com.example.academicherald.models.User;
import com.example.academicherald.repositories.CommentRepository;
import com.example.academicherald.repositories.PublicationRepository;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final PublicationRepository publicationRepository;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepository,
                          PublicationRepository publicationRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.publicationRepository = publicationRepository;
        this.userRepository = userRepository;
    }

    public Comment create(Comment comment, Long userId, Long publicationId) {
        User author = userRepository.findById(userId).orElse(null);
        Publication publication = publicationRepository.findById(publicationId).orElse(null);
        comment.setUser(author);
        comment.setPublication(publication);
        return commentRepository.save(comment);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findByIdAndRdtIsNull(id);
    }

    public Comment updateComment(Comment newComment) {
        Comment oldComment = getCommentById(newComment.getId());
        oldComment.setText(newComment.getText());
        return commentRepository.save(oldComment);
    }

    //Вытаскиваем список комментариев к определенному посту по id
    public List<Comment> allCommentsByIdPublic(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId).orElse(null);
        List<Comment> comments = commentRepository.findByPublicationAndRdtIsNull(publication);
        return comments;
    }

    //Вытаскиваем список комментариев который написал пользователь за все время
    public List<Comment> allCommentsByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Comment> comments = commentRepository.findByUserAndRdtIsNull(user);
        return comments;
    }

    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        comment.setRdt(LocalDateTime.now());
        commentRepository.save(comment);
    }

}
