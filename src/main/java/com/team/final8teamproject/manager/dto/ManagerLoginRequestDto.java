package com.team.final8teamproject.manager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class ManagerLoginRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
