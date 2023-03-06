package com.team.final8teamproject.business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class BusinessRequestDto {
    private String b_no;
    private String start_dt;
    private String p_nm;
    private String b_nm;

    public BusinessRequestDto(String b_no, String start_dt, String p_nm, String b_nm) {
        this.b_no = b_no;
        this.start_dt = start_dt;
        this.p_nm = p_nm;
        this.b_nm = b_nm;
    }

    public String getB_no() {
        return b_no;
    }

    public String getStart_dt() {
        return start_dt;
    }

    public String getP_nm() {
        return p_nm;
    }

    public String getB_nm() {
        return b_nm;
    }

}