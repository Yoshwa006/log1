package com.example.demo.controller;


import com.example.demo.model.Users;
import com.example.demo.service.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class Controller {

    private final MainService mainService;


    @Autowired
    public Controller(MainService mainService) {
        this.mainService = mainService;
    }

    @PostMapping("/register")
    public String registerLog(@RequestBody Users user) {
        boolean valid = mainService.register(user);
        if (valid) {
            return "User registered successfully";
        } else {
            return "Registration failed: User already exists";
        }
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        boolean passed = mainService.login(user);
        if (passed)
            return "User logged in successfully";
        else {
            return "Login failed: Invalid credentials";
        }
    }

    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId) {
        boolean deleted = mainService.deleteUser(userId);
        if (deleted) {
            return "User deleted successfully";
        } else {
            return "Deletion failed: User not found";
        }
    }

    @PostMapping("/test")
    public String test(@RequestBody String message) {
        mainService.test(message);
        return "Test message sent successfully";
    }
}
