package com.team.final8teamproject.gymboardreview.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GymDeleteReview {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String comment;
    private Long gymId;
    private Long reviewId;


    /**
     * 
     * @param gymId 운동시설 gymBoard의 id값
     * @param username 작성자의 username(email로 변경될수있음)
     * @param comment 내용 
     * @param reviewId review에서 작성된 id 값
     */
    @Builder
    public GymDeleteReview(Long gymId, String username,
                           String comment, Long reviewId){
        this.username = username;
        this.comment = comment;
        this.gymId = gymId;
        this.reviewId = reviewId;
    }
}
