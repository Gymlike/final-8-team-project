package com.team.final8teamproject.user.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "KaKao")
@DiscriminatorValue(value = "KaKao")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class KaKao extends BaseEntity {

    private Long kakaoId;
    private String profileImage;
    private String nickName;
    private Long experience = 0L;

    @Builder
    public KaKao(Long kakaoId, String username, String password, UserRoleEnum role,
                String nickName, String email,
                Long experience){
        super(username, password, email, role);
        this.kakaoId = kakaoId;
        this.nickName = nickName;
        this.experience = experience;
    }

    //    카카오 사용자
    public KaKao KakaoIdUpdate(Long kakaoId) {
        this.kakaoId = kakaoId;
        return this;
    }

}
