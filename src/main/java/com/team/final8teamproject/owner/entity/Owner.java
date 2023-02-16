package com.team.final8teamproject.owner.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import com.team.final8teamproject.user.entity.User;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "owner")
@DiscriminatorValue(value = "Owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends BaseEntity {

    private String profileImage;

    @Column(nullable = false)
    private String storeName;
    @Column(nullable = false)
    private String ownerNumber;

    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Long experience;

    //오너생성
    @Builder
    public Owner(String username, String password, UserRoleEnum role,
                String nickName, String phoneNumber, String email,
                Long experience, String storeName, String ownerNumber){
        super(username, password, email, role);
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
        this.storeName= storeName;
        this.ownerNumber = ownerNumber;
    }

    //프로필이미지 수정
    public void changeOwnerProfile(ProfileModifyRequestDto profileModifyRequestDto) {
        this.nickName = profileModifyRequestDto.getNickname();
        this.profileImage = profileModifyRequestDto.getImage();
        this.phoneNumber = profileModifyRequestDto.getPhoneNumber();
    }

}
