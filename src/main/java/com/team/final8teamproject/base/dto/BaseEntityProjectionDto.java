package com.team.final8teamproject.base.dto;

import com.team.final8teamproject.user.entity.UserRoleEnum;

public interface BaseEntityProjectionDto {
    String getUsername();
    String getNickName();
    String getProfileImage();
    String getEmail();
    String getPassword();
    Long getId();
    UserRoleEnum getRole();
}