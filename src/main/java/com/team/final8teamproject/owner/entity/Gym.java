package com.team.final8teamproject.owner.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "gym")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Gym {
    @Id
    @Column(name = "Gym_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String ownerNumber;
    @Column(nullable = false)
    private String gymName;
    @Column(nullable = false)
    private String comment;
    //가격 어떻게 해야하는지
    private String price;
    private String convenientFacilities;
    private String phoneNumber;
    @Column(nullable = false)
    private String address;
    private String gymPhoto;
    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<GymReview> categories = new ArrayList<>();
    //나중에 트레이너 추가하기
}
