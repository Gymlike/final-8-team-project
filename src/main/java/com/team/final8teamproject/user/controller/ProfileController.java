package com.team.final8teamproject.user.controller;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.service.BaseService;
import com.team.final8teamproject.board.dto.ImageNameDTO;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.share.aws_s3.PresignedUrlService;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.dto.ProfileResponseDto;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.service.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    private final PasswordEncoder passwordEncoder;
    private final BaseService baseService;
    private String path;
    private final PresignedUrlService presignedUrlService;

    //유저
    //1. 프로필 조회
    @GetMapping("")
    public ProfileResponseDto getProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return profileService.getProfile(userDetailsImpl.getBase().getId());
    }

    //2. 프로필 수정
    @PatchMapping("")
    public void modifyProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid ProfileModifyRequestDto profileModifyRequestDto) {
        String imageUrl = presignedUrlService.findByName(path);
        profileService.modifyProfile(profileModifyRequestDto,userDetails.getBase().getId(),imageUrl);
    }

    //관리자
    //1. 프로필 조회
    @GetMapping("/manager")
    public ProfileResponseDto getManagerProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return profileService.getManagerProfile(userDetailsImpl.getBase().getId());
    }

    //2. 프로필 수정
    @PatchMapping("/manager")
    public void modifyManagerProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid ProfileModifyRequestDto profileModifyRequestDto) {
        String imageUrl = presignedUrlService.findByName(path);
        profileService.modifyManagerProfile(profileModifyRequestDto,userDetails.getBase().getId(),imageUrl);
    }

    @GetMapping("/kakao")
    public ProfileResponseDto getKakaoProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return profileService.getKakaoProfile(userDetailsImpl.getBase().getId());
    }

//    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ이전 버전ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//    //유저
//    //1. 프로필 조회
//    @GetMapping()
//    public ProfileResponseDto getProfile(@Valid @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//
//        return profileService.getProfile((User) userDetailsImpl.getBase());
//    }
//
//
//    //2. 프로필 수정
//    @PostMapping()
//    public void modifyProfile(@Valid @RequestBody ProfileModifyRequestDto profileModifyRequestDto
//            , @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        profileService.modifyProfile(profileModifyRequestDto, (User) userDetailsImpl.getBase());
//    }

//    //사업자
//    //1. 프로필 조회
//    @GetMapping("/owner")
//    public ProfileResponseDto getOwnerProfile(@Valid @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//
//        return profileService.getOwnerProfile((Owner) userDetailsImpl.getBase());
//    }
//
//    //2. 프로필 수정
//    @PostMapping("/owner")
//    public void modifyOwnerProfile(@Valid @RequestBody ProfileModifyRequestDto profileModifyRequestDto
//            , @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        profileService.modifyOwnerProfile(profileModifyRequestDto, (Owner) userDetailsImpl.getBase());
//    }

//    //관리자
//    //1. 프로필 조회
//    @GetMapping("/manager")
//    public ProfileResponseDto getManagerProfile(@Valid @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//
//        return profileService.getManagerProfile((Manager) userDetailsImpl.getBase());
//    }
//
//    //2. 프로필 수정
//    @PostMapping("/manager")
//    public void modifyManagerProfile(@Valid @RequestBody ProfileModifyRequestDto profileModifyRequestDto
//            , @AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
//        profileService.modifyManagerProfile(profileModifyRequestDto, (Manager) userDetailsImpl.getBase());
//    }

    //유저 프로필 진입 비밀번호 확인
    @PostMapping("/check-password")
    public void checkPassword(@RequestBody Map<String, String > password, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        User user = (User) userDetails.getBase();
        if (user == null || !passwordEncoder.matches(password.get("password"),user.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 다릅니다");
        }
    }

    @GetMapping("/password")
    public String showPasswordForm(@RequestParam(name = "error", defaultValue = "0") int error, Model model) {
        model.addAttribute("error",error);
        return "password";
    }
    //프리사인 이용할꺼임
    @PostMapping("/presigned")
    public String creatPresigned(@RequestBody ImageNameDTO imageNameDTO,
                                 @AuthenticationPrincipal UserDetailsImpl userDetails) {
        BaseEntity base = userDetails.getBase();

        boolean checkUser = baseService.checkUser(base.getUsername());

        if(checkUser){
            path ="usermypage";
            String imageName = imageNameDTO.getImageName();
            return presignedUrlService.getPreSignedUrl(path,imageName);
        }else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }
}
