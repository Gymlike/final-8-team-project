package com.team.final8teamproject.user.dto;

import lombok.Getter;

@Getter
public class FindByResponseDto {
    private String username;
    public FindByResponseDto(String username){
        this.username = username;
    }
}