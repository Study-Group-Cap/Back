package com.Study_Group.App_Backend.repository;

import com.Study_Group.App_Backend.entity.ChatRoom;
import com.Study_Group.App_Backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    Optional<ChatRoom> findByUser1AndUser2(User user1, User user2);
    Optional<ChatRoom> findByUser2AndUser1(User user1, User user2);
}
