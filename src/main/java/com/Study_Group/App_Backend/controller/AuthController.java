package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.dto.LoginRequest;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "로그인", description = "사용자의 ID와 비밀번호로 로그인하고 토큰을 발급받습니다.")
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        User user = authService.login(request);
        return ResponseEntity.ok("로그인 성공: " + user.getUsername());
    }
}
