package com.team.final8teamproject.manager.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
//lombok
@Entity(name = "Manager")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@DiscriminatorValue(value = "Manager")
public class Manager extends BaseEntity{
    private String profileImage;
    @NotBlank
    private String nickName;

    @Builder
    public Manager(String username, String password, String nickName, UserRoleEnum role) {
        super(username, password, role);
        this.nickName = nickName;
    }

}
