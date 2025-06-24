package com.Study_Group.App_Backend.dto;

import com.Study_Group.App_Backend.entity.Notification;
import lombok.Getter;

@Getter
public class NotificationResponse {
    private String message;
    private Long lostItemId;
    private Long foundItemId;
    private String createdAt;

    public NotificationResponse(Notification n) {
        this.message = n.getMessage();
        this.lostItemId = n.getLostItemId();
        this.foundItemId = n.getFoundItemId();
        this.createdAt = n.getCreatedAt();
    }
}
