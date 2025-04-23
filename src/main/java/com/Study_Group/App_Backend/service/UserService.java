package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.LoginRequest;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean login(LoginRequest request) {
        return userRepository.findByUserId(request.getUserId())
                .map(user -> user.getPassword().equals(request.getPassword()))
                .orElse(false);
    }

    public Optional<User> findByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }
}