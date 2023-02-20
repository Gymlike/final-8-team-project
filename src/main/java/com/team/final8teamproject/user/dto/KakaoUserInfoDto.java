package com.team.final8teamproject.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private Long id;
    private String username;
    private String email;
    private String nickname;

    public KakaoUserInfoDto(Long id, String nickname, String email) {
        this.id = id;
        this.username = "kakao" + id;
        this.nickname = nickname;
        this.email = email;
    }

}