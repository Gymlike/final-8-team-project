package com.team.final8teamproject.manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String managerName;
    @JsonIgnore
    @NotBlank
    private String password;
    private String profileImage;
    @NotBlank
    private String nickname;
    private ManagerRoleEnum role;

    @Builder
    public Manager(String managerName, String password, String nickname, ManagerRoleEnum role) {
        this.managerName = managerName;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public void approvalManager(ManagerRoleEnum role) {
        this.role = role;
    }
}
