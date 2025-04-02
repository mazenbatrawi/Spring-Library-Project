package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public User addUser(
            @RequestBody User user
    ) {
        return userService.addUser(user);
    }

    @GetMapping("/find/{id}")
    public User getUserById(
            @PathVariable int id
    ) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(
            @PathVariable int id
    ) {
        userService.deleteUserById(id);
    }

    @GetMapping()
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/update/{id}")
    public User updateUser(
            @PathVariable Integer id,
            @RequestBody User user
    ) {
        return userService.updateUser(id, user);
    }
}
