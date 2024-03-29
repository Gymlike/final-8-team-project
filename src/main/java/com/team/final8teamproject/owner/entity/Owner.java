package com.team.final8teamproject.owner.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;

@Getter
@Entity(name = "owner")
@DiscriminatorValue(value = "Owner")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@BatchSize(size = 100) // 추가
public class Owner extends BaseEntity {

    private String profileImage;

    private String ownerName;

    private String ownerNumber;
    private String phoneNumber;

    private String storeName;

    @Column(nullable = false)
    private Long experience = 0L;

    private String start_dt;

    //오너생성
    @Builder
    public Owner(String username, String password, UserRoleEnum role,
                 String nickName, String phoneNumber, String email,
                 String storeName, String ownerName,
                 String ownerNumber, String start_dt) {
        super(username, password, email, role, nickName);
        this.phoneNumber = phoneNumber;
        this.ownerName = ownerName;
        this.ownerNumber = ownerNumber;
        this.storeName = storeName;
        this.start_dt = start_dt;
    }

    public void modifyOwnerProfile(String password,
                                   String nickName, String phoneNumber, String email,
                                   String storeName, String ownerNumber, String profileImage) {
        super.modifyBaseProfile(email, password);
        changeNickName(nickName);
        this.phoneNumber = phoneNumber;
        this.ownerNumber = ownerNumber;
        this.storeName = storeName;
        this.profileImage = profileImage;
    }

    public boolean isPasswordVaild(String password) {
        return getPassword().equals(password);
    }


}
