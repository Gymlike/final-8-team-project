package com.team.final8teamproject.share.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CustomException extends  RuntimeException{
    private final ExceptionStatus exceptionStatus;
}
