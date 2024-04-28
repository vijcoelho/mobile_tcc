package com.example.tcc.controller;

import com.example.tcc.model.MyUser;
import com.example.tcc.model.MyUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForgotPasswordController {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("/forgot_password")
    public MyUser changePassword(HttpServletRequest request) {
        String newPassword = request.getParameter("password");

        MyUser user = new MyUser();
        user.setPassword(passwordEncoder.encode(newPassword));

        return myUserRepository.save(user);
    }
}
