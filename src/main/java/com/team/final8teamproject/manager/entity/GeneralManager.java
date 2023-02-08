package com.team.final8teamproject.manager.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import net.minidev.json.annotate.JsonIgnore;

@Entity
public class GeneralManager {
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
}
