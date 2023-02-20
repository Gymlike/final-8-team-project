package com.team.final8teamproject.share.exception.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class ErrorDTO {
    private final int statusCode;
    private final String message;

}