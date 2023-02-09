package com.team.final8teamproject.manager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Getter
public class Manager {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String manager;
    @JsonIgnore
    @NotBlank
    private String password;
    private String profileImage;
    @NotBlank
    private String nickname;
    private ManagerRoleEnum role;

    @Builder
    public Manager(String manager, String password, String nickname, ManagerRoleEnum role) {
        this.manager = manager;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
    }

    public void approvalManager(ManagerRoleEnum role) {
        this.role = role;
    }
}
