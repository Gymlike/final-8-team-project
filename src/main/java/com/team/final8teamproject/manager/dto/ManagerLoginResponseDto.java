package com.team.final8teamproject.manager.dto;

import lombok.Getter;

@Getter
public class ManagerLoginResponseDto {
    private String message;

    public ManagerLoginResponseDto(String message){
        this.message = message;
    }
}
