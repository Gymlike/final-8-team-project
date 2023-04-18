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
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private Amenities amenities;
    private String amenitiesDetail;
    private Long rating;
    private String discount;
    //나중에 트레이너 추가하기

    @Builder(builderMethodName = "CreateGymBoard")
    public GymBoard(CreatePostGymRequestDto requestDto,
                    String imgUrl,
                    String username){
        this.username = username;
        updateTitle(requestDto.getTitle());
        updateOwnerNumber(requestDto.getOwnerNumber());
        updateGymName(requestDto.getGymName());
        updateContent(requestDto.getContents());
        updateImageUrl(imgUrl);
        updateRegion(requestDto.getRegion());
        updateOpenTime(requestDto.getOpenTime());
        updateAmenitiesDetail(requestDto.getAmenitiesDetail());
        updateAmenities(requestDto.getAmenities());
        updatePrice(requestDto.getPrice());
        this.rating =0L;
    }

    @Builder(builderMethodName = "GymBoard")
    public GymBoard(String title, String username, String gymName,String content,
                    String image, String ownerNumber, String region,
                    String openTime, Amenities amenities, String amenitiesDetail,
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
        updateAmenities(requestDto.getAmenities());
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
    public void updatePrice(String price){
        this.price = price;
    }
    public void updateRating(Long rating){
        this.rating = rating;
    }
    private void updateContent(String content){this.content = content;}
    private void updateOwnerNumber(String ownerNumber){this.ownerNumber = ownerNumber;}
    private void updateRegion(String region){this.region = region;}
    private void updateGymName(String gymName){this.gymName = gymName;}
    private void updateImageUrl(String imageUrl){this.imageUrl = imageUrl;}
    private void updateTitle(String title){this.title = title;}
    private void updateOpenTime(String openTime){this.openTime = openTime;}
    private void updateAmenities(Amenities amenities){
        this.amenities = amenities;
    }
    private void updateAmenitiesDetail(String amenitiesDetail){this.amenitiesDetail = amenitiesDetail;}
    private void updateInLive(boolean inLive){this.inLive = inLive;}
}
