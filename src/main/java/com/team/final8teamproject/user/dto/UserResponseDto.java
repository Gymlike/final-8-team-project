package com.team.final8teamproject.user.dto;


import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.dto.BaseEntityProjectionDto;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@NoArgsConstructor
public class UserResponseDto implements Serializable {
    Long userId;
    String email;
    String nickname;
    @Setter
    String profileImageUrl;
    String username;
    UserRoleEnum role;

    /**
     * 생성자
     */
    private UserResponseDto(BaseEntity user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.nickname = user.getNickName();
        this.role = user.getRole();
    }

    private UserResponseDto(BaseEntityProjectionDto user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.nickname = user.getNickName();
        this.role = user.getRole();
    }

    public static UserResponseDto of(BaseEntityProjectionDto baseEntityProjectionDto){
        return new UserResponseDto(baseEntityProjectionDto);
    }
    /**
     * 유저 객체를 DTO에 담아 반환해줍니다.
     * @param user 유저 객체
     * @return UserResponseDto
     */
    public static UserResponseDto of(BaseEntity user) {
        return new UserResponseDto(user);
    }
}
