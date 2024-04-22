package com.example.tcc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContentController {

    @GetMapping("/login")
    public String handleLogin() {
        return "custom_login";
    }

    @GetMapping("/user/home")
    public String handleUser() {
        return "home_user";
    }

    @GetMapping("/admin/home")
    public String handleAdmin() {
        return "home_admin";
    }

    @GetMapping("/home")
    public String handleHome() {
        return "home";
    }
}
