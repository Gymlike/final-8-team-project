package com.team.final8teamproject.manager.dto;

import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import lombok.Getter;

@Getter
public class ManagerResponseDto {
    private Long id;
    private UserRoleEnum role;
    private String nickname;

    public ManagerResponseDto(Manager manager) {
        this.id = manager.getId();
        this.role = manager.getRole();
        this.nickname = manager.getNickName();
    }
}
