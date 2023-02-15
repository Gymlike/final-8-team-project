package com.team.final8teamproject.share.exception;

import com.team.final8teamproject.share.exception.dto.ApiExceptionDTO;
import com.team.final8teamproject.share.exception.dto.ErrorDTO;
import io.jsonwebtoken.security.SecurityException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

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

//    @ExceptionHandler()
//    @ResponseStatus()
//    public ApiExceptionDTO SizeException(){
//        log.warn(e.getMessage());
//        return new ApiExceptionDTO();
//    }
    @ResponseStatus(HttpStatus.NOT_FOUND)
    //상태코드를 400으로 집어넣어줌
    @ExceptionHandler(IllegalArgumentException.class)
    protected ApiExceptionDTO handleIllegalArgumentException(IllegalArgumentException e) {
        log.warn(e.getMessage());
        return new ApiExceptionDTO(e.getMessage(), 404);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(SecurityException.class)
    public ApiExceptionDTO SecurityExceptionExceptionHandling(SecurityException exception) {
        log.warn(exception.getMessage());
        return new ApiExceptionDTO(exception.getMessage(),403);
    }
    @ExceptionHandler({NullPointerException.class})
    protected ResponseEntity handleNullPointerException(java.lang.Exception e) {
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

    @ExceptionHandler({NoSuchElementException.class})
    protected ApiExceptionDTO handleMtehodNotFindExcpetion(NoSuchElementException e){
        log.warn(e.getMessage());
        return new ApiExceptionDTO(e.getMessage(), 403);
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    protected ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.warn(e.getMessage());
        return new ResponseEntity<>(e.getBindingResult().getFieldError().getDefaultMessage(), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler({RuntimeException.class})
    protected ResponseEntity<String> handleEtcException(RuntimeException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
