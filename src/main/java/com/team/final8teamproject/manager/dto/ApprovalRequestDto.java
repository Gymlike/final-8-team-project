package com.team.final8teamproject.manager.dto;

import lombok.Getter;

@Getter
public class ApprovalRequestDto {
    private String manager;

    public String getManager(){
        return manager;
    }
}
