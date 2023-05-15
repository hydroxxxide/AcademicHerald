package com.example.academicherald.services;

import com.example.academicherald.models.User;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User create(User user){
        return repository.save(user);
    }

    public User getById(Long id){
        return repository.findById(id).orElse(null);
    }

    public User update(User newUser){
        User u2 = getById(newUser.getId());
        u2.setUsername(newUser.getUsername());
        u2.setEmail(newUser.getEmail());
        return repository.save(u2);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
