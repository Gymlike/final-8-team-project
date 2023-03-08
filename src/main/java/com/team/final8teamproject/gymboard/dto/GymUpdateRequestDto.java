package com.team.final8teamproject.gymboard.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class GymUpdateRequestDto {
    private String title;
    private String gymName;
    private String content;
    private String region;
    private String imageUrl;
    private String openTime;
    private String amenities;
    private String amenitiesDetail;
}
