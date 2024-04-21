package com.example.tcc.controller;

import com.example.tcc.model.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.tcc.model.User;

@RestController
public class UserController {

    @Autowired
    private UserDao userDao;

    @PostMapping("user/save")
    public User save(@RequestBody User user) {
        return userDao.save(user);
    }

    @PostMapping("user/login")
    public ResponseEntity<String> authUser(@RequestBody User user) {
        User authUser = userDao.authUser(user.getUserEmail(), user.getUserPassword());

        if (authUser != null) {
            String response = "Welcome, you're authenticated";
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Authentication failed");
        }
    }

    @GetMapping("user/getCurrentUser")
    public User getCurrentUser() {
        return userDao.getCurrentUser();
    }
}
