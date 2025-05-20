package com.Study_Group.App_Backend.controller;

import com.Study_Group.App_Backend.entity.ChatRoom;
import com.Study_Group.App_Backend.service.ChatRoomService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/chatrooms")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService = chatRoomService;
    }

    // 채팅방 입장 or 생성 API
    @PostMapping("/enter/{targetUserId}")
    public ResponseEntity<Long> enterChatRoom(@PathVariable String targetUserId, HttpSession session) {
        // 현재 로그인 사용자 ID 가져오기
        String currentUserId = (String) session.getAttribute("userId");

        if (currentUserId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        // 채팅방 생성 or 조회
        ChatRoom room = chatRoomService.getOrCreateRoom(currentUserId, targetUserId);

        return ResponseEntity.ok(room.getId()); // 프론트에 채팅방 ID 반환
    }
}
