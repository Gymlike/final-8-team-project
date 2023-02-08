package com.team.final8teamproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ExceptionAdviceHandler {

    @ExceptionHandler({CustomException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity handleApiException(CustomException customException) {
        return new ResponseEntity(new ErrorDTO(
                customException.getExceptionStatus().getStatusCode(),
                customException.getExceptionStatus().getMessage()),
                HttpStatus.valueOf(customException.getExceptionStatus().getStatusCode()));
    }


    //    @ExceptionHandler({IllegalArgumentException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
//        ApiExceptionDto apiExceptionDto = new ApiExceptionDto();
//        apiExceptionDto.setErrorMessage(e.getMessage());
//        apiExceptionDto.setHttpStatus(HttpStatus.BAD_REQUEST);
//        log.warn(e.getMessage());
//        return new ResponseEntity(apiExceptionDto, HttpStatus.BAD_REQUEST);
//    }
    @ExceptionHandler({NullPointerException.class})
    protected ResponseEntity handleNullPointerException(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
//    @ExceptionHandler({NullPointerException.class})
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    protected ResponseEntity handleNullPointerException(Exception e) {
//        ApiExceptionDto apiExceptionDto = new ApiExceptionDto();
//        apiExceptionDto.setErrorMessage(e.getMessage());
//        apiExceptionDto.setHttpStatus(HttpStatus.BAD_REQUEST);
//        return new ResponseEntity(apiExceptionDto, HttpStatus.BAD_REQUEST);
//    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<String> handleEtcException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
