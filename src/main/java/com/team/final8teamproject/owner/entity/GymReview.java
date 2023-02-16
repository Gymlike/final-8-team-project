package com.team.final8teamproject.owner.entity;

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
    @ManyToOne
    @JoinColumn(name="Gym_ID", nullable = false)
    private Gym gym;
    @Builder
    public GymReview(Gym gym, String username){
        this.username = username;
        this.gym = gym;
    }
}
