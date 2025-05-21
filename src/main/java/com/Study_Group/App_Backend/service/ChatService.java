package com.Study_Group.App_Backend.service;

import com.Study_Group.App_Backend.dto.ChatMessage;
import com.Study_Group.App_Backend.entity.ChatMessageEntity;
import com.Study_Group.App_Backend.repository.ChatMessageRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {
    private final ChatMessageRepository chatMessageRepository;

    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void saveMessage(ChatMessage message) {
        ChatMessageEntity entity = new ChatMessageEntity();
        entity.setRoomId(message.getRoomId());
        entity.setSender(message.getSender());
        entity.setContent(message.getContent());
        entity.setTimestamp(LocalDateTime.now());
        entity.setFileUrl(message.getFileUrl());
        entity.setType(ChatMessageEntity.MessageType.valueOf(message.getType().name()));

        chatMessageRepository.save(entity);
    }

    public List<ChatMessageEntity> getMessagesByRoomId(String roomId) {
        return chatMessageRepository.findByRoomIdOrderByTimestampAsc(roomId);
    }
}