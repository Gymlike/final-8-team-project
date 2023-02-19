package com.team.final8teamproject.gymboard.entity;

import com.team.final8teamproject.gymboard.dto.CreatePostGymRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @Builder(builderMethodName = "GymBoard")
    public GymBoard(String title, String username, String gymName,String content, String image, String ownerNumber, String region) {
        this.title = title;
        this.ownerNumber= ownerNumber;
        this.gymName = gymName;
        this.username = username;
        this.content = content;
        this.gymImage = image;
        this.region = region;
    }

 @Builder(builderMethodName = "gymBoardUpdate")
    public void update(CreatePostGymRequestDto createPostGymRequestDto, String username){
        this.title = createPostGymRequestDto.getTitle();
        this.username = username;
        this.content = createPostGymRequestDto.getContents();
        this.gymImage = createPostGymRequestDto.getImage();
        this.region = createPostGymRequestDto.getRegion();
    }
}
