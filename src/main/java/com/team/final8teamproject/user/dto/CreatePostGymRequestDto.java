package com.team.final8teamproject.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(force = true)
public class CreatePostGymRequestDto {
    private final String title;
    private final String writer;
    private final String contents;
    private final String trainer;
    private final String region;
    private final String image;
}
