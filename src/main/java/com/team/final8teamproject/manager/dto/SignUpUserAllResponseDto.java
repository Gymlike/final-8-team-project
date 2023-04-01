package com.team.final8teamproject.manager.dto;

import com.team.final8teamproject.base.entity.BaseEntity;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class SignUpUserAllResponseDto implements Serializable {

    private Long id;
    private String username;
    private String nickName;
    private String time;
    public SignUpUserAllResponseDto(BaseEntity entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
        this.nickName = entity.getNickName();
        this.time = entity.getCreatedDateString();
    }

}
