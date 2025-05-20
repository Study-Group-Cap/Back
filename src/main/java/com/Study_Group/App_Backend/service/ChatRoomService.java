package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.entity.ChatRoom;
import com.Study_Group.App_Backend.entity.User;
import com.Study_Group.App_Backend.repository.ChatRoomRepository;
import com.Study_Group.App_Backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;

    public ChatRoomService(ChatRoomRepository chatRoomRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
    }

    public ChatRoom getOrCreateRoom(String currentUserId, String targetUserId) {
        User currentUser = userRepository.findByUserId(currentUserId)
                .orElseThrow(() -> new RuntimeException("현재 사용자 정보를 찾을 수 없습니다."));

        User targetUser = userRepository.findByUserId(targetUserId)
                .orElseThrow(() -> new RuntimeException("대상 사용자 정보를 찾을 수 없습니다."));

        // user1-user2 또는 user2-user1 조합으로 채팅방 조회
        Optional<ChatRoom> existingRoom =
                chatRoomRepository.findByUser1AndUser2(currentUser, targetUser)
                        .or(() -> chatRoomRepository.findByUser1AndUser2(targetUser, currentUser));

        return existingRoom.orElseGet(() -> {
            ChatRoom newRoom = new ChatRoom();
            newRoom.setUser1(currentUser);
            newRoom.setUser2(targetUser);
            return chatRoomRepository.save(newRoom);
        });
    }
}