package com.team.final8teamproject.base.entity;


import com.team.final8teamproject.share.Timestamped;
import com.team.final8teamproject.user.entity.UserRoleEnum;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
public class BaseEntity extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_ID")
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    public BaseEntity(String username, String password, String email, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.email=email;
        this.role = role;
    }

    public BaseEntity(String username, String password, UserRoleEnum role){
        this.username = username;
        this.password = password;
        this.role = role;
    }
    public void changePassword(String password){
        this.password = password;
    }

    public boolean isUserId(Long userid) {
        return this.id.equals(userid);
    }

    public String getWriterName() {
        return this.username;
    }

    public void changeRole1(UserRoleEnum role) {
        this.role = role;
    }

}
