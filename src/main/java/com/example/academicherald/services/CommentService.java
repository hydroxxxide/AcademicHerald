package com.example.academicherald.services;

import com.example.academicherald.models.Comment;
import com.example.academicherald.models.Publication;
import com.example.academicherald.models.User;
import com.example.academicherald.repositories.CommentRepository;
import com.example.academicherald.repositories.PublicationRepository;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.stereotype.Service;

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
        return commentRepository.findById(id).orElse(null);
    }

    public Comment updateComment(Comment newComment) {
        Comment oldComment = commentRepository.getById(newComment.getId());
        oldComment.setText(newComment.getText());
        oldComment.setUser(newComment.getUser());
        oldComment.setPublication(newComment.getPublication());
        return commentRepository.save(oldComment);
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }


    public List<Comment> allCommentsByIdPublic(Long publicationId) {
        Publication publication = publicationRepository.findById(publicationId).orElse(null);
        List<Comment> comments = commentRepository.findByPublication(publication);
        return comments;
    }

    public List<Comment> allCommentsByUser(Long userId) {
        User user = userRepository.findById(userId).orElse(null);
        List<Comment> comments = commentRepository.findByUser(user);
        return comments;
    }

}
