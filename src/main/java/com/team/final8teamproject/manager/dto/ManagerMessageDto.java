package com.team.final8teamproject.manager.dto;

import lombok.Getter;

@Getter
public class ManagerMessageDto {
    private String message;

    public ManagerMessageDto(String message){
        this.message = message;
    }
}
