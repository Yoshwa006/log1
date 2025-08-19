package com.example.demo.service;

import com.example.demo.model.Payload;
import com.example.demo.model.Users;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    private final UserRepository repo;
    private final LogService logService;

    @Autowired
    public MainService(UserRepository repo, LogService logService) {
        this.repo = repo;
        this.logService = logService;
    }

    private void sendLog(String event, String userId, String message, String level) {
        Payload payload = new Payload();
        payload.setEvent(event);
        payload.setUserID(userId);
        payload.setServiceName("user-service");
        payload.setTimestamp(String.valueOf(System.currentTimeMillis()));
        payload.setMessage(message);
        payload.setLevel(level); // INFO, WARN, ERROR

        logService.sendLog("user-logs", payload);
    }

    public boolean register(Users user) {
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            sendLog("USER_ALREADY_EXISTS", user.getUsername(),
                    "User already exists with username: " + user.getUsername(),
                    "WARN");
            return false;
        }

        Users savedUser = repo.save(user);
        sendLog("USER_CREATED", String.valueOf(savedUser.getId()),
                "User created successfully",
                "INFO");
        return true;
    }

    public boolean login(Users user) {
        Users existingUser = repo.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            sendLog("USER_LOGIN_SUCCESS", String.valueOf(existingUser.getId()),
                    "User logged in successfully",
                    "INFO");
            return true;
        } else {
            sendLog("USER_LOGIN_FAILED", user.getUsername(),
                    "Login failed for user: " + user.getUsername(),
                    "ERROR");
            return false;
        }
    }

    public boolean deleteUser(Long userId) {
        if (repo.existsById(userId)) {
            repo.deleteById(userId);
            sendLog("USER_DELETED", String.valueOf(userId),
                    "User deleted successfully",
                    "INFO");
            return true;
        } else {
            sendLog("USER_DELETE_FAILED", String.valueOf(userId),
                    "User deletion failed, user not found with ID: " + userId,
                    "WARN");
            return false;
        }
    }
}
