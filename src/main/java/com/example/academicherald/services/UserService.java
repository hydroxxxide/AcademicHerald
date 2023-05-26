package com.example.academicherald.services;

import com.example.academicherald.enums.UserRole;
import com.example.academicherald.models.User;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, EmailService emailService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findByIdAndRdtIsNull(id).orElseThrow(EntityNotFoundException::new);
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
    public List<User> getAllGuests() {
        return userRepository.findAllByRole(UserRole.GUEST);
    }
    public List<User> getAllStudent(){
        return userRepository.findAllByRole(UserRole.STUDENT);
    }
    public List<User> getAllMentors() {
        return userRepository.findAllByRole(UserRole.MENTOR);
    }
    public List<User> getAllReviewer(){
        return userRepository.findAllByRole(UserRole.REVIEWER);
    }
    public List<User> getAllManager(){
        return userRepository.findAllByRole(UserRole.MANAGER);
    }


    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean resetPassword(String email) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            return false;
        }

        String resetToken = UUID.randomUUID().toString();
        user.setResetToken(resetToken);
        user.setResetTokenExpireTime(LocalDateTime.now().plusMinutes(60));
        userRepository.save(user);

        String resetUrl = "http://localhost:8080/auth/reset/form/" + resetToken;
        String emailText = "Здравствуйте, " + user.getUsername() +
                "\nДля сброса пароля перейдите по ссылке: " + resetUrl;

        emailService.sendSimpleMessage(email, "Сброс пароля", emailText);
        return true;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public boolean saveNewPassword(String resetToken, String newPassword) {
        User user = userRepository.findByResetToken(resetToken);
        if (user == null || user.getResetTokenExpireTime().isBefore(LocalDateTime.now()))
            return false;

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpireTime(null);
        userRepository.save(user);
        return true;
    }

    public boolean isPresentUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user != null;
    }
}
