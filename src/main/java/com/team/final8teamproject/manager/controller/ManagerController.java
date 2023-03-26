package com.team.final8teamproject.manager.controller;

import com.team.final8teamproject.manager.dto.ManagerResponseDto;
import com.team.final8teamproject.manager.dto.ManagerSignupRequestDto;
import com.team.final8teamproject.security.jwt.JwtUtil;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.user.dto.*;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import com.team.final8teamproject.manager.service.ManagerService;
import com.team.final8teamproject.manager.service.ManagerServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/manager")
@PreAuthorize("hasRole('ROLE_MANGER')")
public class ManagerController {

    private final ManagerService managerService;
    private final ManagerServiceImpl managerServiceImpl;
    private final JwtUtil jwtUtil;

    //1. 회원가입
    @PostMapping("/signup")
    public MessageResponseDto signup(@RequestBody @Valid ManagerSignupRequestDto managerSignupRequestDto) {
        if (!managerSignupRequestDto.getPassword().equals((managerSignupRequestDto.getPassword2()))) {
            throw new IllegalArgumentException("일치X");
//         bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다");
        }
        return managerServiceImpl.signUp(managerSignupRequestDto);
    }

    //2.로그인
    @PostMapping("/login")
    public MessageResponseDto login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        //이름과 유저인지 관리자인지 구분한 토큰을 가져오는 부분
        TokenResponseDto msg = managerServiceImpl.login(loginRequestDto);
        //문자열 token에 가져온 정보를 넣어주는 부분
        String token = msg.getAtk();
        //헤더를 통해 토큰을 발급해 주는 부분
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token);
        return new MessageResponseDto("로그인 되었습니다.");
    }

    //3. 로그아웃
    @DeleteMapping("/logout")
    public MessageResponseDto logout(@AuthenticationPrincipal UserDetailsImpl userDetails
            , HttpServletRequest request){
        String accessToken = jwtUtil.resolveToken(request);
        return new MessageResponseDto(managerServiceImpl.logout(accessToken, userDetails.getUsername()));
    }

    //4. 매니저 가입대기자 조회
    @GetMapping("/standby")
    public List<ManagerResponseDto> getStandByList() {
        return managerService.getStandByList(UserRoleEnum.WAIT);
    }

    //5. 매니저 가입 승인
    @PatchMapping("/standby/{id}")
    public void allowStandBy(@PathVariable Long id){
        managerService.allowStandBy(id);
    }

    //6. 매니저 가입 거부
    @DeleteMapping("/standby/{id}")
    public void refuseStandBy(@PathVariable Long id){
        managerService.refuseStandBy(id);
    }
}
