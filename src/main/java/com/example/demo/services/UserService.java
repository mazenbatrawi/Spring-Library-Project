package com.example.demo.services;

import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User updateUser(Integer id, User user) {
        if (user == null) {
            return null;
        }
        User updatedUser = userRepository.findById(id).orElse(null);
        if (updatedUser != null) {
            if (user.getFirstName() != null) {
                updatedUser.setFirstName(user.getFirstName());
            }
            if (user.getLastName() != null) {
                updatedUser.setLastName(user.getLastName());
            }
            if (user.getEmail() != null) {
                updatedUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                updatedUser.setPassword(user.getPassword());
            }
            if (user.getUsername() != null) {
                updatedUser.setUsername(user.getUsername());
            }

            return userRepository.save(updatedUser);
        }
        return null;
    }
}
