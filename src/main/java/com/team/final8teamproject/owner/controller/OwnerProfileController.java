package com.team.final8teamproject.owner.controller;


import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.base.service.BaseService;
import com.team.final8teamproject.board.dto.ImageNameDTO;
import com.team.final8teamproject.owner.dto.OwnerProfileModifyRequestDto;
import com.team.final8teamproject.owner.dto.OwnerProfileResponseDto;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.owner.service.OwnerProfileService;
import com.team.final8teamproject.security.service.UserDetailsImpl;
import com.team.final8teamproject.share.aws_s3.PresignedUrlService;
import com.team.final8teamproject.share.exception.CustomException;
import com.team.final8teamproject.share.exception.ExceptionStatus;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/owners")
public class OwnerProfileController {
    private final OwnerProfileService ownerProfileService;
    private final PasswordEncoder passwordEncoder;
    private final BaseService baseService;
    private String path;
    private final PresignedUrlService presignedUrlService;

    //1. 프로필 조회
    @GetMapping("")
    public OwnerProfileResponseDto getMyOwnerProfile(@AuthenticationPrincipal UserDetailsImpl userDetailsImpl) {
        return ownerProfileService.getMyOwnerProfile(userDetailsImpl.getBase().getId());
    }

    //2. 프로필 수정
    @PatchMapping("")
    public void modifyMyOwnerProfile(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody @Valid OwnerProfileModifyRequestDto ownerProfileRequestDto) {
        String imageUrl = presignedUrlService.findByName(path);
        ownerProfileService.modifyMyOwnerProfile(ownerProfileRequestDto,userDetails.getBase().getId(),imageUrl);
    }

    //3. 프로필 진입 비밀번호 확인
    @PostMapping("/check-password")
    public void checkPassword(@RequestBody Map<String, String > password, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Owner owner = (Owner) userDetails.getBase();
        if (owner == null || !passwordEncoder.matches(password.get("password"),owner.getPassword())) {
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
            path ="ownermypage";
            String imageName = imageNameDTO.getImageName();
            return presignedUrlService.getPreSignedUrl(path,imageName);
        }else {
            throw new CustomException(ExceptionStatus.WRONG_USERNAME);
        }
    }
}
