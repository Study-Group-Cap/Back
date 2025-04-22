package com.Study_Group.App_Backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SignupRequest {
    private String userId;
    private String password;
}
