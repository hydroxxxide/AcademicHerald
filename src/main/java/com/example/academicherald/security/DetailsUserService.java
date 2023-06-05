package com.example.academicherald.security;


import com.example.academicherald.entity.User;
import com.example.academicherald.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DetailsUserService implements UserDetailsService {
   private final UserRepository userRepository;

   @Autowired
    public DetailsUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    @Override
    public DetailsUser loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Unknown user: " + username);
        }
        return new DetailsUser(user);
    }
}
