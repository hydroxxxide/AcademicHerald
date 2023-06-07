package com.example.academicherald.services;

import com.example.academicherald.entity.Like;
import com.example.academicherald.entity.Publication;
import com.example.academicherald.entity.User;
import com.example.academicherald.repositories.LikeRepository;
import com.example.academicherald.repositories.PublicationRepository;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PublicationRepository publicationRepository;
    private final LikeRepository likeRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       PublicationRepository publicationRepository,
                       LikeRepository likeRepository,
                       EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.publicationRepository = publicationRepository;
        this.likeRepository = likeRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findByIdAndRdtIsNull(id);
    }

    public List<User> getAll() {
        return userRepository.findAllByRdtIsNull();
    }

    public User update(User newUser) {
        User oldUser = getById(newUser.getId());
        oldUser.setUsername(newUser.getUsername());
        oldUser.setEmail(newUser.getEmail());
        oldUser.setRole(newUser.getRole());
        oldUser.setPublications(newUser.getPublications());
        return userRepository.save(oldUser);
    }

    public void delete(Long id) {
        User user = getById(id);
        user.setRdt(LocalDateTime.now());
        userRepository.save(user);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String resetPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return "Пользователя с таким адресом эл.почты не существует";
        }

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        user.setResetTokenExpireTime(LocalDateTime.now().plusMinutes(60));
        userRepository.save(user);

        String resetUrl = "http://localhost:9090/auth/reset/" + resetToken;
        String emailText = "Здравствуйте, " + user.getUsername() +
                "\n\nДля сброса пароля перейдите по ссылке: " + resetUrl;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);
        return "Ссылка для сброса пароля была отправлена ваш на адрес электронной почты " + email;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public String saveNewPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return "Ссылка для сброса пароля устарела или не найден пользователь";

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepository.save(user);
        return "Пароль успешно изменен!";
    }

    public boolean isPresentUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }

    public String likePublication(Long publicationId, Long userId){
        User user = getById(userId);
        Publication publication = publicationRepository.findById(publicationId).orElseThrow();
        Like like = new Like();
        if (likeRepository.findByPublicationIdAndUserId(publicationId, userId) == null){
            like.setUser(user);
            like.setPublication(publication);
            publication.getLikes().add(like);
            likeRepository.save(like);
        }else{
            publication.getLikes().remove(likeRepository.findByPublicationIdAndUserId(publicationId, userId));
            likeRepository.delete(likeRepository.findByPublicationIdAndUserId(publicationId, userId));
        }
        return "Пользователь " + user.getUsername() + " лайкнул публикацию " + "\"" + publication.getTitle() + "\"";
    }
}
