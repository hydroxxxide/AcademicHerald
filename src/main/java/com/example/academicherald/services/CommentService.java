package com.example.academicherald.services;

import com.example.academicherald.entity.Comment;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.entity.User;
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
        User author = userRepository.findByIdAndRdtIsNull(userId);
        Publication publication = publicationRepository.findByIdAndPassAndRdtIsNull(publicationId, true);
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
        Publication publication = publicationRepository.findByIdAndPassAndRdtIsNull(publicationId, true);
        return commentRepository.findByPublicationAndRdtIsNull(publication);
    }

    //Вытаскиваем список комментариев который написал пользователь за все время
    public List<Comment> allCommentsByUser(Long userId) {
        User user = userRepository.findByIdAndRdtIsNull(userId);
        return commentRepository.findByUserAndRdtIsNull(user);
    }

    public String deleteComment(Long commentId, Long userId) throws Exception {
        Comment comment = getCommentById(commentId);
        if (userId.equals(comment.getUser().getId())) {
            comment.setRdt(LocalDateTime.now());
            commentRepository.save(comment);
            return "Комментарий удален";
        } else throw new Exception("Пользователь не является автором комментария");
    }

}
