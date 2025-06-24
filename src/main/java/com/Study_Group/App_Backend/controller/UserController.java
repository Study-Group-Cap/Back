package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.dto.NotificationResponse;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<User> getMyInfo(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @GetMapping("/{userId}/notifications")
    public ResponseEntity<List<NotificationResponse>> getMyNotifications(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getMyNotificationsByUserId(userId));
    }

    @PatchMapping("/{userId}/profile-image")
    public ResponseEntity<String> uploadProfileImage(@PathVariable Long userId,
                                                     @RequestParam MultipartFile file) throws IOException {
        String uploadPath = "uploads/profile/";
        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();

        File targetFile = new File(uploadPath + fileName);
        targetFile.getParentFile().mkdirs();
        file.transferTo(targetFile);

        String imageUrl = "/static/" + fileName;
        userService.updateProfileImage(userId, imageUrl);
        return ResponseEntity.ok(imageUrl);
    }
}
