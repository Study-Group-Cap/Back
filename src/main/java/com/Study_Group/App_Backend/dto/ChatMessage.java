package com.Study_Group.App_Backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ChatMessage {

    public enum MessageType {
        CHAT,
        JOIN,
        LEAVE,
        FILE
    }

    private MessageType type;
    private String roomId;
    private String sender;
    private String content;
    private String fileUrl;
}
