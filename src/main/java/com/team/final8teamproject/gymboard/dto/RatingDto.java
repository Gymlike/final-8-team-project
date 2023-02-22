package com.team.final8teamproject.gymboard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    private Long total;
    private Long count;

    public RatingDto(Long total, Long count) {
        this.total += total;
        this.count += count;
    }

}
