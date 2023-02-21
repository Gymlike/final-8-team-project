package com.team.final8teamproject.owner.dto;

import com.team.final8teamproject.owner.entity.Owner;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerProfileResponseDto {
    private Long id;
    private String username;
    private String nickName;
    private String email;
    private String phoneNumber;
    private String storeName;
    private String profileImage;
    private String ownerNumber;
    private Long experience;

    private OwnerProfileResponseDto(Owner owner) {
        this.id = owner.getId();
        this.username = owner.getUsername();
        this.nickName = owner.getNickName();
        this.email = owner.getEmail();
        this.phoneNumber = owner.getPhoneNumber();
        this.storeName = owner.getStoreName();
        this.profileImage = owner.getProfileImage();
        this.ownerNumber = owner.getOwnerNumber();
        this.experience = owner.getExperience();
    }

    public static OwnerProfileResponseDto of(Owner owner) {
        return new OwnerProfileResponseDto(owner);
    }
}
