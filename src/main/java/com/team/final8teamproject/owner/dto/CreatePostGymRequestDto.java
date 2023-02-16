package com.team.final8teamproject.owner.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Getter
@NoArgsConstructor(force = true)
public class CreatePostGymRequestDto {
    private final String title;
    private final String username;
    private final String contents;
    private final String ownerNumber;
    private final String gymName;
    private final String region;
    private final String image;
}
