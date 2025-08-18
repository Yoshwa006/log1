package com.example.demo.service;


import com.example.demo.model.Payload;
import com.example.demo.model.Users;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MainService {

    UserRepository repo;
    LogService logService;


    @Autowired
    public MainService(UserRepository repo , LogService logService) {
        this.logService = logService;
        this.repo = repo;
    }

    public boolean register(Users user) {
        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return false;
        }

        Users savedUser = repo.save(user);

        Payload payload = new Payload();
        payload.setEvent("USER_CREATED");
        payload.setUserID(String.valueOf(savedUser.getId()));
        payload.setTimestamp(String.valueOf(System.currentTimeMillis()));
//        logService.sendLogForRegister("user-logs", payload);

        return true;
    }


    public boolean login(Users user) {
        Users existingUser = repo.findByUsername(user.getUsername()).orElse(null);
        if (existingUser != null && existingUser.getPassword().equals(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public boolean deleteUser(Long userId) {
        if (repo.existsById(userId)) {
            repo.deleteById(userId);
            Payload payload = new Payload();
            payload.setEvent("USER_DELETED");
            payload.setUserID(String.valueOf(userId));
            payload.setTimestamp(String.valueOf(System.currentTimeMillis()));
//            logService.sendLogForRegister("user-logs", payload);
            return true;
        } else {
            return false;
        }
    }
}
