package com.team.final8teamproject.owner.dto;

import com.team.final8teamproject.owner.entity.Trainer;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class TrainerResponseDto {
    private String trainername;
    private String content;
    private String storeName;
    private String image;


    private TrainerResponseDto(Trainer trainer) {
        this.trainername = trainer.getTrainername();
        this.content = trainer.getContents();
        this.storeName = trainer.getStoreName();
        this.image = trainer.getImage();
    }

    public static TrainerResponseDto of(Trainer trainer) {
        return new TrainerResponseDto(trainer);
    }
}
