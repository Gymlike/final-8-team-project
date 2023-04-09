package com.team.final8teamproject.user.dto;

import com.team.final8teamproject.user.entity.UserRoleEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginResponseDto {

    private String grantType;
    private String accessToken;
    private String refreshToken;
    private Long refreshTokenExpirationTime;
    private UserRoleEnum role;

    @Builder
    public LoginResponseDto(String grantType, String accessToken,
                            String refreshToken, Long refreshTokenExpirationTime,
                            UserRoleEnum role) {
        this.grantType = grantType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.refreshTokenExpirationTime = refreshTokenExpirationTime;
        this.role = role;
    }

}
