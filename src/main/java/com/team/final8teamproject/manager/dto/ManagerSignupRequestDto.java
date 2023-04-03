package com.team.final8teamproject.manager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ManagerSignupRequestDto {

    //정규식이 틀렸을때 발생하는 예외 MethodArgumentNotValidException
    @Pattern(regexp = "[a-zA-Z0-9]{4,10}$", message = "아이디는 최소 4자 이상, 10자 이하이며, 영문과 숫자만 입력하세요.")
    private String username;

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])^[a-zA-Z0-9~!@#$%^&*()+|=]{8,15}$", message = "비밀번호는 최소 8자 이상, 15자 이하이며, 영문과 숫자, 특수문자만 입력하세요.")
    private String password;

    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])^[a-zA-Z0-9~!@#$%^&*()+|=]{8,15}$", message = "비밀번호를 확인해주세요.")
    private String password2;

    private String image;


//    @Size(min=2,max=4, message = "사용자이름은 2~4글자여야 합니다.")
    private String nickName;

    @Pattern(regexp = "(?=.*[0-9])^[0-9]{11}$", message = "-을 제외한 10자리 번호를 입력해주세요")
    private String phoneNumber;

    @Email
    private String email;

    private boolean applyManager;

}
