package com.team.final8teamproject.manager.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@DiscriminatorValue(value = "Manager")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Manager")
public class Manager extends BaseEntity {
    private String profileImage;
//
//    @Column(nullable = false)
//    private String nickName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Long experience;

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRoleEnum;

    private boolean applyManager;

    @Builder
    public Manager(String username, String password, UserRoleEnum role,
                   String nickName, String phoneNumber, String email,
                   Long experience) {
        super(username, password, email, role, nickName);
        this.phoneNumber = phoneNumber;
        this.experience = experience;
        this.userRoleEnum = UserRoleEnum.WAIT;
        applyManager = false;
    }

    //프로필이미지 수정
    public void changeManagerProfile(ProfileModifyRequestDto profileModifyRequestDto) {
        changeNickNme(profileModifyRequestDto.getNickname());
        this.profileImage = profileModifyRequestDto.getImage();
        this.phoneNumber = profileModifyRequestDto.getPhoneNumber();
    }

    //권한 변경
    public void changeRole(UserRoleEnum role) {
        this.userRoleEnum = role;
    }

    //신청 상태 변경
    public void changeApplyStatus(boolean applyManager){
        this.applyManager = applyManager;
    }

}
