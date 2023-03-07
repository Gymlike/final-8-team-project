package com.team.final8teamproject.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProfileModifyRequestDto {
    @NotBlank(message = "닉네임을 입력하세요.")
    private String nickname;

//    @NotBlank(message = "이미지를 등록하세요.")
//    private String image;

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])^[a-zA-Z0-9~!@#$%^&*()+|=]{8,15}$", message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9),특수문자(~!@#$%^&*()+|=)")
    private String password;

}
