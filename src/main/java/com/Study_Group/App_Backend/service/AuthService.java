package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.LoginRequest;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public User login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자입니다."));

        // ✅ 1. 잠금 여부 확인
        if (user.getLockedUntil() != null && user.getLockedUntil().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("비밀번호를 3회 이상 틀려 계정이 잠겼습니다. 잠금 해제까지 남은 시간: " +
                    Duration.between(LocalDateTime.now(), user.getLockedUntil()).getSeconds() + "초");
        }

        // ✅ 2. 비밀번호 검증
        if (!user.getPassword().equals(request.getPassword())) {
            user.setLoginFailCount(user.getLoginFailCount() + 1);

            if (user.getLoginFailCount() >= 3) {
                user.setLockedUntil(LocalDateTime.now().plusMinutes(1));
                user.setLoginFailCount(0); // 실패 횟수 초기화
            }

            userRepository.save(user);
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        // ✅ 3. 로그인 성공 → 실패횟수 초기화
        user.setLoginFailCount(0);
        user.setLockedUntil(null);
        userRepository.save(user);

        return user;
    }

}
