package com.team.final8teamproject.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponseDto {
    private Long id;

    private String username;

    private String nickName;

    private String email;

    private String image;

    private String phoneNumber;

}