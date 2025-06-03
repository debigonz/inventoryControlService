package com.inventory.users.service;

import com.inventory.users.domain.entity.User;
import com.inventory.users.domain.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    public User createUser(User user) throws Exception {
        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExists) {
            log.error("User with ID {} not found", user.getId());
            throw new Exception("User already exists");
        }
        log.info("Creating user: {}", user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User updateUser(Long id) throws UsernameNotFoundException {
        User userExists = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        log.info("Updating user: {}", userExists);
        return userRepository.save(userExists);
    }

    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(id.toString()));
        log.info("Deleting user with ID {}", user.getId());
        userRepository.delete(user);
    }

    public User findUserByEmail(String email) {
        log.info("Finding user with email {}", email);
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public List<User> findAllUsers() {
        log.info("Finding all users");
        return userRepository.findAll();
    }

}
