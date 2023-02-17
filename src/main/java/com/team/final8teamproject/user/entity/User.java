package com.team.final8teamproject.user.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@DiscriminatorValue(value = "Users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")

public class User extends BaseEntity {
    private String profileImage;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private Long experience;

    @Builder
    public User(String username, String password, UserRoleEnum role,
                String nickName, String phoneNumber, String email,
                Long experience){
        super(username, password, email, role);
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
    }

    public void changeProfile(ProfileModifyRequestDto profileModifyRequestDto) {
        this.nickName = profileModifyRequestDto.getNickname();
        this.profileImage = profileModifyRequestDto.getImage();
        this.phoneNumber = profileModifyRequestDto.getPhoneNumber();
    }

}
