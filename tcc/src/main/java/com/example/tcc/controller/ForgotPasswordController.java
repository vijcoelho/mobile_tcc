package com.example.tcc.controller;

import com.example.tcc.model.MyUser;
import com.example.tcc.model.MyUserDetailService;
import com.example.tcc.model.MyUserRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class ForgotPasswordController {

    @Autowired
    private MyUserRepository myUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MyUserDetailService myUserDetailService;

    @PostMapping("/forgot_password")
    public String changePassword(HttpServletRequest request, Model model) throws Exception {
        String newPassword = request.getParameter("password");
        String confirmPassword = request.getParameter("confirm_password");
        String username = request.getParameter("username");
        Optional<MyUser> optionalMyUser = myUserRepository.findUserByUsername(username);

        if (newPassword.equals(confirmPassword)) {
            if (optionalMyUser.isPresent()) {
                MyUser user = optionalMyUser.get();
                user.setPassword(passwordEncoder.encode(newPassword));
                myUserRepository.save(user);
                return "redirect:/success_password";
            } else {
                throw new Exception("User not found");
            }
        } else {
            model.addAttribute("error", "The passwords must be equals");
            return "redirect:/forgot_password";
        }
    }
}
