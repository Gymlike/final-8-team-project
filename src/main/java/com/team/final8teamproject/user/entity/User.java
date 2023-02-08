package com.team.final8teamproject.user.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
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



    @Builder
    public User(String username, String password, UserRoleEnum role, String nickName, String phoneNumber, String email){
        this.username =username;
        this.password = password;
        this.role = role;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

}
