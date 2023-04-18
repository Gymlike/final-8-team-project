package com.team.final8teamproject.gymboard.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.team.final8teamproject.gymboard.entity.GymBoard;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
public class GymPostResponseDto implements Serializable{
    private Long id;
    private String title;
    private String content;
    private String image;
    private String gymName;
    private String region;
    private Long rating;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'작성시간'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime createdDate;

    public GymPostResponseDto(){}
    public GymPostResponseDto(GymBoard gymBoard) {
        this.id = gymBoard.getId();
        this.title = gymBoard.getTitle();
        this.content =gymBoard.getContent();
        this.image =gymBoard.getImageUrl();
        this.gymName =gymBoard.getGymName();
        this.region = gymBoard.getRegion();
        this.createdDate =gymBoard.getCreatedDate();
        this.rating = gymBoard.getRating();
    }
}
