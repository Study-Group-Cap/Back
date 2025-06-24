package com.Study_Group.App_Backend.dto;

import com.Study_Group.App_Backend.entity.FoundItem;
import lombok.Getter;

@Getter
public class FoundItemSummaryResponse {

    private long id;
    private String title;
    private String category;
    private String imageUrl;
    private String createdAt;

    public FoundItemSummaryResponse(FoundItem item) {
        this.id = item.getId();
        this.title = item.getTitle();
        this.category = item.getCategory();
        this.imageUrl = item.getImageUrl();
        this.createdAt = item.getCreatedAt();
    }
}
