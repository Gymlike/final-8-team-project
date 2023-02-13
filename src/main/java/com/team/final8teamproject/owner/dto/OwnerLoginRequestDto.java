package com.team.final8teamproject.owner.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class OwnerLoginRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

}
