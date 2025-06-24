package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.ChangeClubRequest;
import com.Study_Group.App_Backend.dto.ChangePasswordRequest;
import com.Study_Group.App_Backend.dto.UserUpdateRequest;
import com.Study_Group.App_Backend.entity.Notification;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.dto.NotificationResponse;
import com.Study_Group.App_Backend.repository.NotificationRepository;
import com.Study_Group.App_Backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }

    public List<NotificationResponse> getMyNotificationsByUserId(Long userId) {
        User user = getUserById(userId);
        List<Notification> notifications = notificationRepository.findByUser(user);
        return notifications.stream()
                .map(NotificationResponse::new)
                .toList();
    }

    public void updateProfileImage(long userId, String imageUrl) {
        User user = getUserById(userId);
        user.setProfileImageUrl(imageUrl);
        userRepository.save(user);
    }
}
