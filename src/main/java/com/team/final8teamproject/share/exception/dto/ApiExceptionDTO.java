package com.team.final8teamproject.share.exception.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiExceptionDTO {
    private String errorMessage;
    private int Status;
    public  ApiExceptionDTO(String errorMessage, int Status){
        this.errorMessage = errorMessage;
        this.Status = Status;
    }

}
