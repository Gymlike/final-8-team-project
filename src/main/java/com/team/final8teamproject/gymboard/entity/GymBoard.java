package com.team.final8teamproject.gymboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.final8teamproject.gymboard.dto.CreatePostGymRequestDto;
import com.team.final8teamproject.gymboard.dto.GymUpdateRequestDto;
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
     * phoneNumber 전화번호
     * region 주소
     * price 가격
     * rating 리뷰 점수
     * gymImage 이미지 주소 넣어두는거
     * amenities 편의시설 확인 1_2_3_4_5 이런식으로 들어가는데 숫자마다 로커가 있는지 주차장이 있는지가 의미함
     * amenitiesDetail 편의시설 확인 이제 로커가 몇개있다. 주차장자리가 몇개다 시간당 얼마다 라고 적어두는곳
     * 운동시설 리뷰, 가격, 트레이너는 다른 테이블에서 연결없이
     * 다 따로 읽어서 불러옴 
     */
    @Id
    @Column(name = "Gym_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    @JsonIgnore
    private String username;
    @Column(nullable = false)
    @JsonIgnore
    private String ownerNumber;

    @JsonIgnore
    private boolean inLive = true;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String gymName;
    @Column(nullable = false)
    private String content;
    private String phoneNumber;
    @Column(nullable = false)
    private String region;
    private String imageUrl;
    private String price;
    private String openTime;
    private String amenities;
    private String amenitiesDetail;
    private Long rating;
    private String discount;
    //나중에 트레이너 추가하기

    @Builder(builderMethodName = "GymBoard")
    public GymBoard(String title, String username, String gymName,String content,
                    String image, String ownerNumber, String region,
                    String openTime, String amenities, String amenitiesDetail,
                    String price) {
        this.title = title;
        this.ownerNumber= ownerNumber;
        this.gymName = gymName;
        this.username = username;
        this.content = content;
        this.imageUrl = image;
        this.region = region;
        this.openTime = openTime;
        this.amenitiesDetail = amenitiesDetail;
        this.amenities = amenities;
        this.price = price;
        this.rating =0L;
    }
    @Builder(builderMethodName = "CreateGymBoard")
    public GymBoard(CreatePostGymRequestDto requestDto,
                    String imgUrl,
                    String username){
        this.title = requestDto.getTitle();
        this.ownerNumber= requestDto.getOwnerNumber();
        this.gymName = requestDto.getGymName();
        this.username = username;
        this.content = requestDto.getContents();
        this.imageUrl = imgUrl;
        this.region = requestDto.getRegion();
        this.openTime = requestDto.getOpenTime();
        this.amenitiesDetail = requestDto.getAmenitiesDetail();
        this.amenities = requestDto.getAmenities();
        this.price = requestDto.getPrice();
        this.rating =0L;
    }

    @Builder(builderMethodName = "ChangeGymBoard")
    public GymBoard(
            String gymName, String address, String price,
            String discount, String username,
            String title, String ownerNumber, String content){
        this.title = title;
        this.ownerNumber= ownerNumber;
        this.content = content;
        this.gymName = gymName;
        this.region = address;
        this.price = price;
        this.discount = discount;
        this.username = username;
    }

    public void update(GymUpdateRequestDto requestDto, String imageUrl) {
        
        /*
        requestDto.getTitle() != null && !requestDto.getTitle().isEmpty()
        로 되어있던걸 나중에 변경해야한다면 유지보수시 수정하기 쉽게 메소드로 다빼서 변경함
         */
        if (notNullNotEmpty(requestDto.getTitle())) {
            updateTitle(requestDto.getTitle());
        }
        if (notNullNotEmpty(requestDto.getContent())) {
            updateContent(requestDto.getContent());
        }
        if (notNullNotEmpty(requestDto.getRegion())) {
            updateRegion(requestDto.getRegion());
        }
        if (notNullNotEmpty(requestDto.getGymName())) {
            updateGymName(requestDto.getGymName());
        }
        if (notNullNotEmpty(imageUrl)) {
            updateImageUrl(imageUrl);
        }
        if (notNullNotEmpty(requestDto.getOpenTime())) {
            updateOpenTime(requestDto.getOpenTime());
        }
        if (notNullNotEmpty(requestDto.getAmenitiesDetail())){
            updateAmenitiesDetail(requestDto.getAmenitiesDetail());
        }
        if (notNullNotEmpty(requestDto.getAmenities())) {
            updateAmenities(requestDto.getAmenities());
        }
    }

    private boolean notNullNotEmpty(String vaild){
        return vaild != null && !vaild.isEmpty();
    }
    public void changeLive(boolean live){
        updateInLive(live);
    }

    public void discountUpdate(String discount){
        this.discount = this.discount + discount;
    }
    public void discountDelete(String discount){
        this.discount = discount;
    }
    public void priceUpdate(String price){
        this.price = price;
    }
    public void ratingUpdate(Long rating){
        this.rating = rating;
    }
    private void updateContent(String content){this.content = content;}
    private void updateRegion(String region){this.region = region;}
    private void updateGymName(String gymName){this.gymName = gymName;}
    private void updateImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    private void updateTitle(String title){this.title = title;}
    private void updateOpenTime(String openTime){this.openTime = openTime;}
    private void updateAmenities(String amenities){this.amenities = amenities;}
    private void updateAmenitiesDetail(String amenitiesDetail){this.amenitiesDetail = amenitiesDetail;}
    private void updateInLive(boolean inLive){this.inLive = inLive;}
}
