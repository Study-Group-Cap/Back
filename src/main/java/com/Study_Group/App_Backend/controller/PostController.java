package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.dto.PostRequest;
import com.Study_Group.App_Backend.entity.Post;
import com.Study_Group.App_Backend.entity.PostStatus;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.UserRepository;
import com.Study_Group.App_Backend.repository.PostRepository;
import com.Study_Group.App_Backend.service.PostService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;
    private final UserRepository userRepository; // UserRepository 추가
    private final PostRepository postRepository; // PostRepository 추가

    public PostController(PostService postService, UserRepository userRepository, PostRepository postRepository) {
        this.postService = postService;
        this.userRepository = userRepository;
        this.postRepository = postRepository;
    }

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody PostRequest request, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Optional<User> user = userRepository.findByUserId(userId); // 수정된 부분
        if (user.isEmpty()) {
            return ResponseEntity.badRequest().body("사용자 정보를 찾을 수 없습니다.");
        }

        Post post = new Post();
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setCategory(request.getCategory());
        post.setAuthor(user.get());

        postRepository.save(post); // 게시글 저장

        return ResponseEntity.ok("게시글 등록 완료");
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{postId}/resolve")
    public ResponseEntity<String> markPostResolved(@PathVariable Long postId, HttpSession session) {
        String userId = (String) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("로그인이 필요합니다.");
        }

        Optional<Post> post = postService.findById(postId);
        if(post.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
        }

        Post foundPost = post.get();
        foundPost.setStatus(PostStatus.RESOLVED);
        postService.save(foundPost);

        return ResponseEntity.ok("게시글을 해결 된 상태로 변경했습니다.");
    }
}