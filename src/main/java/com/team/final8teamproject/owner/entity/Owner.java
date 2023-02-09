package com.team.final8teamproject.owner.entity;

import com.team.final8teamproject.user.entity.Timestamped;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "Owners")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Owner extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "workoutGymOwner")
    private Long id;
    @Column(nullable = false, unique = true)
    private String ownerName;
    @Column(nullable = false)
    private String password;
    private String image;
    @Column(nullable = false)
    private String nickName;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;
    @Column(nullable = false)
    private String phoneNumber;
    @Column(nullable = false)
    private String email;

    //운동시설 이름
    @Column(nullable = false)
    private String storeName;

    //사업자 번호
    @Column(nullable = false)
    private String storeNumber;

    @Builder
    public Owner(String ownerName, String password, UserRoleEnum role, String nickName, String phoneNumber, String email, String storeName, String storeNumber) {
        this.ownerName = ownerName;
        this.password = password;
        this.role = role;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.storeName = storeName;
        this.storeNumber = storeNumber;
    }
}
