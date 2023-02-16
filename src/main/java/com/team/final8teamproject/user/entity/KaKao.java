package com.team.final8teamproject.user.entity;

import com.team.final8teamproject.base.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "KaKao")
@DiscriminatorValue(value = "KaKao")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class KaKao extends BaseEntity {
    @Column(nullable = false)
    private Long id;
    private String profileImage;
    private String nickName;
    private Long experience = 0L;
}
