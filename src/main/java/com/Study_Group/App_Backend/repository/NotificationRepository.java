package com.Study_Group.App_Backend.repository;

import com.Study_Group.App_Backend.entity.Notification;
import com.Study_Group.App_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByUserId(Long userId);
    List<Notification> findByUser(User user);
}
