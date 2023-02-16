package com.team.final8teamproject.owner.entity;

import com.team.final8teamproject.owner.dto.CreatePostGymRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class GymBoard {
    @Id
    @Column(name = "Gym_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String ownerNumber;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String gymName;
    @Column(nullable = false)
    private String content;
    //가격 어떻게 해야하는지
    private String price;
    private String convenientFacilities;
    private String phoneNumber;
    @Column(nullable = false)
    private String region;
    private String gymImage;

    //나중에 트레이너 추가하기

    @Builder
    public GymBoard(String title, String username, String gymName,String content, String image, String ownerNumber, String region) {
        this.title = title;
        this.ownerNumber= ownerNumber;
        this.gymName = gymName;
        this.username = username;
        this.content = content;
        this.gymImage = image;
        this.region = region;
    }

    public void update(CreatePostGymRequestDto createPostGymRequestDto){
        this.title = createPostGymRequestDto.getTitle();
        this.username = createPostGymRequestDto.getUsername();
        this.content = createPostGymRequestDto.getContents();
        this.gymImage = createPostGymRequestDto.getImage();
        this.region = createPostGymRequestDto.getRegion();
    }
}
