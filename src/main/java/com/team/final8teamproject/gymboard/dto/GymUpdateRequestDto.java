package com.team.final8teamproject.gymboard.dto;

import com.team.final8teamproject.gymboard.entity.Amenities;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class GymUpdateRequestDto implements Serializable {
    private String title;
    private String content;
    private String region;
    private String gymName;
    private String imageUrl;
    private String openTime;
    private String amenitiesDetail;
    private Amenities amenities;
    public GymUpdateRequestDto(){}
    public GymUpdateRequestDto(String title, String content,
                               String region, String gymName,
                               String imageUrl, String openTime,
                               String amenitiesDetail, Amenities amenities) {
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
