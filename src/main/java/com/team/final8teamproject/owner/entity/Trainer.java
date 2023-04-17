package com.team.final8teamproject.owner.entity;

import com.team.final8teamproject.owner.dto.TrainerRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Trainer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Trainer_ID")
    private Long id;

    @Column(nullable = false)
    private String trainername;

    @Column(nullable = false)
    private String storeName;

    @Column
    private String contents;

    @Column
    private String image;

    @ManyToOne
    private Owner owner;

    @Builder
    public Trainer(String trainername, String storeName, String contents, String image) {
        this.trainername = trainername;
        this.storeName = storeName;
        this.contents = contents;
        this.image = image;
    }
    public void setOwner(Owner owner){
        this.owner = owner;
    }
    public void updateTrainer(TrainerRequestDto trainerRequestDto) {
        this.trainername = trainerRequestDto.getTrainername();
        this.storeName = trainerRequestDto.getStoreName();
        this.contents = trainerRequestDto.getContents();
        this.image = trainerRequestDto.getImage();
    }
}
