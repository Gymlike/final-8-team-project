package com.team.final8teamproject.manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GeneralManager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String generalName;
    @JsonIgnore
    @NotBlank
    private String password;
    private String profileImage;
    @NotBlank
    private String nickname;

    @NotBlank
    private ManagerRoleEnum role;
    @Builder
    public GeneralManager(String generalName, String password, String nickname, ManagerRoleEnum role){
        this.generalName = generalName;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

}
