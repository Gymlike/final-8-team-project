package com.team.final8teamproject.user.entity;

import com.team.final8teamproject.user.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "POST_ID")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
//    @ManyToOne
    private String username;


    private int likeCount;

    @Column(nullable = false)
    private String content;
    private String password;
    //게시글 참조하는 User관계 설정
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private User users;

    //양방향에 데이터를 저장해주기 위해서 사용한 메소드
    //그런데 오류나서 죽여뒀다.
    public void addUser(User user) {
        this.users = user;
//        users.getPosts().add(this);
    }

    public Post(PostRequestDto requestDto, String username, User user) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.username = username;
        this.users = user;
    }
}