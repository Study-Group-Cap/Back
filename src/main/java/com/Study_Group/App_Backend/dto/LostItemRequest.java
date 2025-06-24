package com.Study_Group.App_Backend.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LostItemRequest {
    private String title;
    private String content;
    private String category;
    private String imageUrl;
}
