package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.dto.LoginRequest;
import com.Study_Group.App_Backend.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginRequest request, Model model,HttpSession session) {
        boolean result = userService.login(request);
        if (result) {
            session.setAttribute("userId", request.getUserId());
            return "redirect:/posts/new";
        } else {
            model.addAttribute("error", "아이디 또는 비밀번호가 틀렸습니다.");
            return "login";
        }
    }

    @GetMapping("/hello")
    public String hello(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            return "redirect:/login";
        }

        userService.findByUserId(userId).ifPresent(user ->
        model.addAttribute("name", user.getName())
        );

        return "hello";
    }
}
