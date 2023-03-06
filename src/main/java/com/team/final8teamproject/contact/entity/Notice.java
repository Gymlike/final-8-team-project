package com.team.final8teamproject.contact.entity;

import com.team.final8teamproject.share.Timestamped;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//공지사항
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice extends Timestamped {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private Long managerId;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;

    private String imageUrl;


    @Builder
    public Notice(Long managerId, String title, String content,String imageUrl) {
        this.managerId = managerId;
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }


    public void update(String title, String content, String imageUrl){
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }


}
