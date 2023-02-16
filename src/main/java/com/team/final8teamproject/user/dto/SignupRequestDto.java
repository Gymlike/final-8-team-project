package com.team.final8teamproject.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {

//    회원가입 비밀번호 재확인 추가

    //정규식이 틀렸을때 발생하는 예외 MethodArgumentNotValidException
    @NotBlank // null 과 "" 과 " " 모두 비허용, @Notnull = "" 이나 " " 은 허용, @NotEmpty = null 과 "" 은 불가, " " 은 허용
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])^[a-z0-9]{4,10}$", message = "최소 4자 이상, 10자 이하이며, 영문과 숫자만 입력하세요.")
    private String username;

    @NotBlank
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])^[a-zA-Z0-9~!@#$%^&*()+|=]{8,15}$", message = "최소 8자 이상, 15자 이하이며, 영문과 숫자, 특수문자만 입력하세요.")
    private String password;

    @NotBlank
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])^[a-zA-Z0-9~!@#$%^&*()+|=]{8,15}$", message = "비밀번호가 일치하지 않습니다.")
    private String password2;

    private String image;

    @NotBlank
//    @Size(min=2,max=4, message = "사용자이름은 2~4글자여야 합니다.")
    private String nickName;

    @NotBlank
    @Pattern(regexp = "(?=.*[0-9])^[0-9]{11}$", message = "-을 제외한 번호를 입력하세요.")
    private String phoneNumber;

    @NotBlank
    @Email
    private String email;

    private Long experience = 0L;
    private boolean admin = false;
    private String adminToken = "";

}