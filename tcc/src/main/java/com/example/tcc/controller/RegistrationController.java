package com.example.tcc.controller;

import com.example.tcc.model.MyUser;
import com.example.tcc.model.MyUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register/user")
    public MyUser createUser(HttpServletRequest request) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        MyUser myUser = new MyUser();
        myUser.setUsername(username);
        myUser.setEmail(email);
        myUser.setPassword(passwordEncoder.encode(password));
        myUser.setRole(role);

        return myUserRepository.save(myUser);
    }
}
