package com.Study_Group.App_Backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PostRequest {
    private String title;
    private String content;
    private String type;
}
