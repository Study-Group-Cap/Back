package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.LoginRequest;
import com.Study_Group.App_Backend.dto.SignupRequest;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean register(SignupRequest request) {
        if (userRepository.findByUserId(request.getUserId()).isPresent()) {
            return false;
        }

        User user = new User();
        user.setUserId(request.getUserId());
        user.setPassword(request.getPassword());

        userRepository.save(user);
        return true;
    }

    public boolean login(LoginRequest request) {
        return userRepository.findByUserId(request.getUserId())
                .map(user -> user.getPassword().equals(request.getPassword()))
                .orElse(false);
    }

}