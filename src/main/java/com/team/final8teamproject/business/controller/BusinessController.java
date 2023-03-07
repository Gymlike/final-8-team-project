package com.team.final8teamproject.business.controller;

import com.team.final8teamproject.business.dto.BusinessRequestDto;
import com.team.final8teamproject.business.service.BusinessService;
import com.team.final8teamproject.share.exception.CustomException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BusinessController {

    private final BusinessService businessService;

    public BusinessController(BusinessService businessService) {
        this.businessService = businessService;
    }

    @PostMapping("/validate")
    public String validateBusiness(@RequestBody BusinessRequestDto requestDto) throws CustomException, IOException {
        return businessService.validateBusiness(requestDto.getB_no(), requestDto.getStart_dt(), requestDto.getP_nm(), requestDto.getB_nm());
    }
}