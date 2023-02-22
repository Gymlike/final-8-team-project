package com.team.final8teamproject.manager.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ManagerLoginResponseDto {
//    private String message;
    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long refreshTokenExpirationTime;


    @Builder
    public ManagerLoginResponseDto(String message,String grantType, String accessToken, String refreshToken, Long refreshTokenExpirationTime) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
//        this.message = message;
    }
}
