package com.team.final8teamproject.gymboard.entity;

import com.team.final8teamproject.gymboard.dto.CreatePostGymRequestDto;
import com.team.final8teamproject.share.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GymBoard extends Timestamped {
    /**
     * username 회원 로그인 아이디? or email로 통일할 가능성있음
     * ownerNumber 사업자 번호
     * title 제목
     * gymName 운동시설 이름
     * content 운동시설 소개글
     * convenientFacilities
     * phoneNumber 전화번호
     * region 주소
     * price 가격
     * gymImage 이미지 넣어두는거
     * 운동시설 리뷰, 가격, 트레이너는 다른 테이블에서 연결없이
     * 다 따로 읽어서 불러옴 
     */
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
    private String convenientFacilities;
    private String phoneNumber;
    @Column(nullable = false)
    private String region;
    private String gymImage;

    private String price;
    //나중에 트레이너 추가하기

    @Builder(builderMethodName = "GymBoard")
    public GymBoard(String title, String username, String gymName,String content,
                    String image, String ownerNumber, String region
    ,String price) {
        this.title = title;
        this.ownerNumber= ownerNumber;
        this.gymName = gymName;
        this.username = username;
        this.content = content;
        this.gymImage = image;
        this.region = region;
        this.price = price;
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
