package com.team.final8teamproject.user.dto;

import lombok.Getter;

@Getter
public class FindPasswordRequestDto {
    private String username;
    private String email;

    private String password;

    public void setPassword(String password){
        this.password = password;
    }
}
