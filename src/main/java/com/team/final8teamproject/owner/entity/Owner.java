package com.team.final8teamproject.owner.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "owner")
@DiscriminatorValue(value = "Owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Owner extends BaseEntity {

    private String profileImage;

    private String ownerNumber;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    private String phoneNumber;

    private String storeName;

    @Column(nullable = false)
    private Long experience;

    //오너생성
    @Builder
    public Owner(String username, String password, UserRoleEnum role,
                 String nickName, String phoneNumber, String email,
                 Long experience, String storeName, String ownerNumber) {
        super(username, password, email, role);
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.experience = experience;
        this.ownerNumber = ownerNumber;
        this.storeName = storeName;
    }

    public void modifyOwnerProfile(String password,
                                   String nickName, String phoneNumber, String email,
                                   String storeName, String ownerNumber, String profileImage) {
        super.modifyBaseProfile(email, password);
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.ownerNumber = ownerNumber;
        this.storeName = storeName;
        this.profileImage = profileImage;
    }

}
