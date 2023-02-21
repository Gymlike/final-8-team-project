package com.team.final8teamproject.owner.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OwnerProfileModifyRequestDto {
    @NotBlank(message = "닉네임을 입력하세요.")
    private String nickname;

    @NotBlank(message = "이미지를 등록하세요.")
    private String image;

    @NotBlank(message = "핸드폰 번호를 입력하세요.")
    private String phoneNumber;

    @NotBlank(message = "헬스장 이름을 입력하세요")
    private String storeName;

    @NotBlank(message = "헬스장 번호를 입력하세요.")
    private String storeNumber;

    @NotBlank(message = "이메일을 입력하세요")
    private String email;

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])^[a-zA-Z0-9~!@#$%^&*()+|=]{8,15}$", message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9),특수문자(~!@#$%^&*()+|=)")
    private String password;

    @NotBlank(message = "사업자번호를 입력하세요")
    private String ownerNumber;
}
