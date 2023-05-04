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

    public User update(User u1){
        User u2 = getById(u1.getId());
        u2.setUsername(u1.getUsername());
        u2.setEmail(u1.getEmail());
        return repository.save(u2);
    }

    public void delete(Long id){
        repository.deleteById(id);
    }
}
