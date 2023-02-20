package com.team.final8teamproject.gymboardreview.entity;

import com.team.final8teamproject.share.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GymReview extends Timestamped {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String comment;
    private Long gymId;
    private Long rating;

    /**
     * @param gymId 운동시설 gymBoard의 id값
     * @param username 작성자의 username(email로 변경될수있음)
     * @param comment 내용
     * @param rating 평가한 별점 점수
     */

    @Builder
    public GymReview(Long gymId, String username,
                     String comment, Long rating){
        this.username = username;
        this.comment = comment;
        this.gymId = gymId;
        this.rating = rating;
    }

    public void updateReview(String comment){
        this.comment =comment;
    }
}
