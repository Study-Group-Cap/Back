package com.Study_Group.App_Backend.dto;

import com.Study_Group.App_Backend.entity.LostItem;
import lombok.Getter;

@Getter
public class LostItemSummaryResponse {

    private long id;
    private String title;
    private String category;
    private String imageUrl;
    private String createdAt;

    public LostItemSummaryResponse(LostItem item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.category = item.getCategory();
        this.imageUrl = item.getImageUrl();
        this.createdAt = item.getCreatedAt();
    }
}
