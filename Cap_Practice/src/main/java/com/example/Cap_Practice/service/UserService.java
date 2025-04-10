package com.example.Cap_Practice.service;

import com.example.Cap_Practice.domain.User;
import com.example.Cap_Practice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 회원가입
    public String signup(User user) {
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            return "이미 존재하는 유저명입니다.";
        }

        userRepository.save(user);
        return "회원가입 성공!";
    }

    // 로그인
    public String login(String username, String password) {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            return "존재하지 않는 유저입니다.";
        }

        User user = optionalUser.get();
        if (!user.getPassword().equals(password)) {
            return "비밀번호가 틀렸습니다.";
        }

        return "로그인 성공!";
    }
}
