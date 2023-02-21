package com.team.final8teamproject.manager.dto;

import com.team.final8teamproject.manager.entity.Manager;
import lombok.Getter;

@Getter
public class WaitMangerResponseDto {
    private String manager;
    private String password;
    private String nickname;
    public WaitMangerResponseDto(Manager manager){
        this.manager = manager.getUsername();
        this.password = manager.getPassword();
        this.nickname = manager.getNickName();
    }
}
