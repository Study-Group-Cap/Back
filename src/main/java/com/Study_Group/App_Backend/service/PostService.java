package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.PostRequest;
import com.Study_Group.App_Backend.entity.Post;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.PostRepository;
import com.Study_Group.App_Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    public void createPost(PostRequest request, String userId) {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("사용자 없음"));

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setType(request.getType());
        post.setAuthor(user);

        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
