package com.team.final8teamproject.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class ApiExceptionDTO {
    private String errorMessage;
    private HttpStatus httpStatus;

}
