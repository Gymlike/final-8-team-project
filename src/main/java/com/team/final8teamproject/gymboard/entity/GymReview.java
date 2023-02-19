package com.team.final8teamproject.gymboard.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GymReview {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String comment;
    private Long gymId;
    @Builder
    public GymReview(Long gymId, String username, String comment){
        this.username = username;
        this.comment = comment;
        this.gymId = gymId;
    }
    public void update(String comment){
        this.comment =comment;
    }
}
