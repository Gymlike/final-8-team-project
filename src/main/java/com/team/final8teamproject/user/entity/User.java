package com.team.final8teamproject.user.entity;

import com.team.final8teamproject.user.dto.ProfileModifyRequestDto;
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

    private String profileImage;

    @Column(nullable = false)
    private String nickName;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;


    @Column(nullable = false)
    private Long experience;

    @Builder
    public User(String username, String password, UserRoleEnum role,
                String nickName, String phoneNumber, String email,
                Long experience){
        this.username =username;
        this.password = password;
        this.nickName = nickName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.experience = experience;
        this.role = role;
    }

    public void changeProfile(ProfileModifyRequestDto profileModifyRequestDto) {
        this.nickName = profileModifyRequestDto.getNickname();
        this.profileImage = profileModifyRequestDto.getImage();
        this.phoneNumber = profileModifyRequestDto.getPhoneNumber();
    }

    public boolean isUserId(Long userid) {
        return this.id.equals(userid);
    }

    public String getWriterName() {
        return this.username;
    }
}
