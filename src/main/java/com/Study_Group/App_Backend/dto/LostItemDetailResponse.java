package com.Study_Group.App_Backend.dto;

import com.Study_Group.App_Backend.entity.LostItem;
import lombok.Getter;

@Getter
public class LostItemDetailResponse {
    private Long id;
    private String title;
    private String content;
    private String category;
    private String imageUrl;
    private String createdAt;

    public LostItemDetailResponse(LostItem item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.content = item.getCategory();
        this.category = item.getCategory();
        this.imageUrl = item.getImageUrl();
        this.createdAt = item.getCreatedAt();
    }
}
