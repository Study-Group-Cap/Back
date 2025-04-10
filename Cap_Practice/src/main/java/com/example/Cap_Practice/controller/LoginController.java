package com.example.Cap_Practice.controller;

import com.example.Cap_Practice.domain.User;
import com.example.Cap_Practice.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session) {

        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("username", username);
            return "redirect:/hello";
        } else {
            return "redirect:/login?error=true";
        }
    }
}
