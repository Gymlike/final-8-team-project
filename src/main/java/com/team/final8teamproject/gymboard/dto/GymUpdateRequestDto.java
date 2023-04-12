package com.team.final8teamproject.gymboard.dto;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
public class GymUpdateRequestDto {
    private final String title;
    private final String content;
    private final String region;
    private final String gymName;
    private final String imageUrl;
    private final String openTime;
    private final String amenitiesDetail;
    private final String amenities;

    public GymUpdateRequestDto(String title, String content, String region, String gymName, String imageUrl, String openTime, String amenitiesDetail, String amenities) {
        this.title = title;
        this.content = content;
        this.region = region;
        this.gymName = gymName;
        this.imageUrl = imageUrl;
        this.openTime = openTime;
        this.amenitiesDetail = amenitiesDetail;
        this.amenities = amenities;
    }
}
