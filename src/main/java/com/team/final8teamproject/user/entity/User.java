package com.team.final8teamproject.user.entity;

import com.team.final8teamproject.base.entity.BaseEntity;

import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Getter
@DiscriminatorValue(value = "Users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
@BatchSize(size = 100) // 추가

public class User extends BaseEntity {
    private String profileImage;
    private String phoneNumber;

    @Column(nullable = false)
    private Long experience = 0L;

    @Builder
    public User(String username, String password, UserRoleEnum role,
                String nickName, String phoneNumber, String email){
        super(username, password, email, role, nickName);
        this.phoneNumber = phoneNumber;
    }
    public void modifyProfile(String nickName, String profileImage) {
        changeNickNme(nickName);
        this.profileImage = profileImage;
    }


}
