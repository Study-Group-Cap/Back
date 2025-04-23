package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.dto.PostRequest;
import com.Study_Group.App_Backend.entity.Post;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.PostRepository;
import com.Study_Group.App_Backend.repository.UserRepository;
import com.Study_Group.App_Backend.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public PostController(PostService postService, UserRepository userRepository, PostRepository postRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @GetMapping("/new")
    public String newPostForm(HttpSession session, Model model) {
        String userId = (String) session.getAttribute("userId");
        if(userId == null) {
            return "redirect:/login";
        }
        User user = userRepository.findByUserId(userId)
                        .orElseThrow(() -> new RuntimeException("사용자 없음"));
        model.addAttribute("userName", user.getName());
        model.addAttribute("postRequest", new PostRequest());
        return "new-post";
    }

    @PostMapping
    public String createPost(@ModelAttribute PostRequest request, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return "redirect:/login";
        }
        postService.createPost(request, userId);
        return "redirect:/posts/new";
    }

    @GetMapping
    public String listPosts(Model model) {
        List<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "post-list";
    }
}
