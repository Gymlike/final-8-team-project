package com.team.final8teamproject.contact.entity;


import com.team.final8teamproject.contact.dto.NoticeRequest;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

//공지사항
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Notice {

    /**
     * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
     */
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


    @Builder
    public Notice(Long managerId, String title, String content) {
        this.managerId = managerId;
        this.title = title;
        this.content = content;
    }


    public void update(NoticeRequest noticeRequest){
        this.title = noticeRequest.getTitle();
        this.content = noticeRequest.getContent();
    }

}
