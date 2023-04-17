package com.team.final8teamproject.gymboard.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@NoArgsConstructor(force = true, access = AccessLevel.PROTECTED)
public class CreatePostGymRequestDto {
    @NotBlank
    @NotNull
    private final String title;
    @NotBlank
    @NotNull
    private final String contents;
    @NotBlank
    @NotNull
    private final String ownerNumber;
    @NotBlank
    @NotNull
    private final String gymName;
    @NotBlank
    @NotNull
    private final String region;
    @NotBlank
    @NotNull
    private final String openTime;
    @NotBlank
    @NotNull
    private final String amenities;
    @NotBlank
    @NotNull
    private final String amenitiesDetail;
    @NotBlank
    @NotNull
    private final String price;

    @Builder
    public CreatePostGymRequestDto(String title, String contents,
                                   String ownerNumber, String gymName,
                                   String region, String openTime,
                                   String amenities, String amenitiesDetail,
                                   String price) {

        this.title = title;
        this.contents = contents;
        this.ownerNumber = ownerNumber;
        this.gymName = gymName;
        this.region = region;
        this.amenities  =amenities;
        this.openTime = openTime;
        this.amenitiesDetail = amenitiesDetail;
        this.price = price;
    }
}
