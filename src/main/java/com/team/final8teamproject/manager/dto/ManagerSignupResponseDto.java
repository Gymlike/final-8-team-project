package com.team.final8teamproject.manager.dto;

import lombok.Getter;

@Getter
public class ManagerSignupResponseDto {
    private String message;

    public ManagerSignupResponseDto(String message){
        this.message = message;
    }
}
