package com.team.final8teamproject.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class KakaoUserInfoDto {
    private String id;
    private Long kakaoId;
    private String email;
    private String nickname;

    public KakaoUserInfoDto(Long id, String nickname, String email) {
        this.id = "kakao" + id;
        this.kakaoId = id;
        this.nickname = nickname;
        this.email = email;
    }

}