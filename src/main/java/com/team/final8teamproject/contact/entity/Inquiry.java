package com.team.final8teamproject.contact.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class
Inquiry {

//문의하기
    //1. 회원(유저), 사업자 - 문의글 등록 / 수정 /삭제
    //2. 관리자 - 문의 답변 등록/ 수정 /삭제 / 고객문의글 삭제 기능
/**
 * 컬럼 - 연관관계 컬럼을 제외한 컬럼을 정의합니다.
 */
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "id", nullable = false)
private Long id;


/**
 * 생성자 - 약속된 형태로만 생성가능하도록 합니다.
 */


/**
 * 연관관계 - Foreign Key 값을 따로 컬럼으로 정의하지 않고 연관 관계로 정의합니다.
 */


/**
 * 연관관계 편의 메소드 - 반대쪽에는 연관관계 편의 메소드가 없도록 주의합니다.
 */


/**
 * 서비스 메소드 - 외부에서 엔티티를 수정할 메소드를 정의합니다. (단일 책임을 가지도록 주의합니다.)
 */
}
