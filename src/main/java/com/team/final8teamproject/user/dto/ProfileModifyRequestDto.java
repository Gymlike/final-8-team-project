package com.team.final8teamproject.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfileModifyRequestDto {

    @NotBlank(message = "닉네임을 입력하세요.")
    private String nickname;

    @NotBlank(message = "이미지를 등록하세요.")
    private String image;

    @NotBlank(message = "핸드폰 번호를 입력하세요.")
    private String phoneNumber;

}
