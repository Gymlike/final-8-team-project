package com.team.final8teamproject.gymboard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team.final8teamproject.share.Timestamped;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class GymPrice extends Timestamped {


    @Id
    @Column(name = "GymPrice_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String price;
    @Column(nullable = false)
    private String gymId;
    @Column(nullable = false)
    @JsonIgnore
    private String username;

    /**
     *
     * @param price 새로입력받을 가격
     *              (들어오는형태 1번 1_20000_3_55000_6_100000_12_170000)
     *              1(개월)_20000(원)_3(개월)_55000(원)_6(개월)_100000(원)
     *              보여주는곳에서
     *              저거 넣어주고 뒤에 가격을 앞에 개월수로 나눠서 6개월 100000원 월가격 166000원 상당
     *              (들어오는형태 2번 1_20000~40000_3_55000~110000_6_100000~200000_12_170000~340000)
     *              저거 넣어주고 뒤에 가격을 앞에 개월수로 나눠서 6개월 100000원~200000원 월가격 166000원~332000원 상당
     *              1000원아래로는 버림으로 할지 올림으로 할지는 회의하고결정
     *              보여줄때 if문으로 1(key의 value)의 갯수가 1개면 1번으로 2개면 2번형태로 보여주게 조건을 주면됨.
     * @param gymId 운동시설 id
     * @param username 작성하는사업자(email로 바뀔수도 있음)
     */
    @Builder(builderMethodName = "newPrice")
    public GymPrice(String price, String gymId, String username) {
        this.price=price;
        this.gymId = gymId;
        this.username= username;
    }

    //변경은 id를 탐색해서 변경하는걸로
    @Builder(builderMethodName = "putPrice")
    public GymPrice(String price){
        this.price =price;
    }

}
