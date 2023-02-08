package com.team.final8teamproject.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SignupRequestDto {


    //정규식이 틀렸을때 발생하는 예외 MethodArgumentNotValidException
    @Pattern(regexp = "(?=.*[a-z])(?=.*[0-9])^[a-z0-9]{4,10}$", message = "최소 4자 이상, 10자 이하이며 알파벳 소문자(a~z), 숫자(0~9)")
    private String username;
    @Pattern(regexp = "(?=.*[a-zA-Z])(?=.*[0-9])^[a-zA-Z0-9~!@#$%^&*()+|=]{8,15}$", message = "최소 8자 이상, 15자 이하이며 알파벳 대소문자(a~z, A~Z), 숫자(0~9),특수문자(~!@#$%^&*()+|=)")
    private String password;
    private String nickName;
    @Pattern(regexp = "(?=.*[0-9])^[0-9]{11}$", message = "11122223333형식으로 -를 빼고 입력해주시기 바랍니다.")
    private String phoneNumber;
    private String email;
}