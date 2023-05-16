package com.example.academicherald.services;

import com.example.academicherald.models.User;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return userRepository.findAll();
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
        userRepository.deleteById(id);
    }

}
