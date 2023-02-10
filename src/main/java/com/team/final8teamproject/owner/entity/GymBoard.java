package com.team.final8teamproject.owner.entity;

import com.team.final8teamproject.owner.dto.CreatePostGymRequestDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GymBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GymPost_ID")
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String userName;          //작성자 이름으로 나중에 수정할때 db에서 글 작성자랑 현재 로그인한 작성자랑 맞는지 확인어떻게??

    //운동시설 소개 글
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String image;

    //소속된 트레이너
    @Column(nullable = false)
    private String trainer;

    //주소
    @Column(nullable = false)
    private String region;

    //운동시설 이용가격 적는곳을 또 따로 만들어야 하는가?
//    @Column(nullable = false)
//    private String price;

    @Builder
    public GymBoard(String title, String userName, String content, String image, String trainer, String region) {
        this.title = title;
        this.userName = userName;
        this.content = content;
        this.image = image;
        this.trainer = trainer;
        this.region = region;
    }

    public void update(CreatePostGymRequestDto createPostGymRequestDto){
        this.title = createPostGymRequestDto.getTitle();
        this.userName = createPostGymRequestDto.getUsername();
        this.content = createPostGymRequestDto.getContents();
        this.image = createPostGymRequestDto.getImage();
        this.trainer = createPostGymRequestDto.getTrainer();
        this.region = createPostGymRequestDto.getRegion();
    }
}
