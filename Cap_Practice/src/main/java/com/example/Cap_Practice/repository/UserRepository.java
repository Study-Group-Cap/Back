package com.example.Cap_Practice.repository;

import com.example.Cap_Practice.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username); // 이거 추가
    User findByUsernameAndPassword(String username, String password); // 이건 로그인용
}
