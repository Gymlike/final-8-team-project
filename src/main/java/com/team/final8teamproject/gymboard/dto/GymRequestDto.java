package com.team.final8teamproject.gymboard.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class GymRequestDto {
    private String username;
    private String ownerNumber;
    private String gymName;
    private String comment;
    //가격 어떻게 해야하는지
    //하나씩 할것인지 테이블 만들어서 거기에 저장해서 할것인지
    private String price;
    private String convenientFacilities;
    private String phoneNumber;
    private String address;
    private String gymPhoto;
}
