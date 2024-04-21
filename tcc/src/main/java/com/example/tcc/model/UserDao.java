package com.example.tcc.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserDao {

    @Autowired
    private UserRepository userRepository;

    private User authUser;

    public User save(User user) {
        return userRepository.save(user);
    }

    public User authUser(String email, String password) {
        User user = userRepository.findByUserEmail(email);

        if (user != null && user.getUserPassword().equals(password)) {
            authUser = user;
            return user;
        } else {
            return null;
        }
    }

    public User getCurrentUser() {
        return authUser;
    }
}
