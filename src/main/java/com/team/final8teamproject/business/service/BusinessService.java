package com.team.final8teamproject.business.service;

import com.team.final8teamproject.share.exception.CustomException;

import java.io.IOException;

public interface BusinessService {
    String validateBusiness(String b_no, String start_dt, String p_nm, String b_nm) throws CustomException, IOException;
}