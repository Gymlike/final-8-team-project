package com.team.final8teamproject.user.dto;

import com.team.final8teamproject.manager.entity.Manager;
import com.team.final8teamproject.owner.dto.OwnerProfileResponseDto;
import com.team.final8teamproject.owner.entity.Owner;
import com.team.final8teamproject.user.entity.KaKao;
import com.team.final8teamproject.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ProfileResponseDto {
    private Long id;

    private String username;

    private String nickName;

    private String email;

    private String phoneNumber;

    private String profileImage;

    private Long experience;

    private ProfileResponseDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.nickName = user.getNickName();
        this.email = user.getEmail();
        this.phoneNumber = user.getPhoneNumber();
        this.profileImage = user.getProfileImage();
        this.experience = user.getExperience();
    }

    public static ProfileResponseDto of(User user) {
        return new ProfileResponseDto(user);
    }

    private ProfileResponseDto(Manager manager) {
        this.id = manager.getId();
        this.username = manager.getUsername();
        this.nickName = manager.getNickName();
        this.email = manager.getEmail();
        this.phoneNumber = manager.getPhoneNumber();
        this.profileImage = manager.getProfileImage();
        this.experience = manager.getExperience();
    }

    public static ProfileResponseDto of(Manager manager) {
        return new ProfileResponseDto(manager);
    }

    private ProfileResponseDto(KaKao kaKao) {
        this.id = kaKao.getId();
        this.username = kaKao.getUsername();
        this.nickName = kaKao.getNickName();
        this.email = kaKao.getEmail();
        this.profileImage = kaKao.getProfileImage();
        this.experience = kaKao.getExperience();
    }

    public static ProfileResponseDto of(KaKao kaKao) {
        return new ProfileResponseDto(kaKao);
    }

//    ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ이전 버전ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ
//    private Long id;
//
//    private String username;
//
//    private String nickName;
//
//    private String email;
//
//    private String image;
//
//    private String phoneNumber;
}