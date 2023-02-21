package com.team.final8teamproject.manager.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneralManager extends BaseEntity {
    private String profileImage;
    @NotBlank
    private String nickName;

    @Builder
    public GeneralManager(String username, String password, String nickName, UserRoleEnum role){
        super(username, password, role);
        this.nickName = nickName;
    }

}
