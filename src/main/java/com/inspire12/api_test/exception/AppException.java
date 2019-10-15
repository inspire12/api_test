package com.inspire12.api_test.exception;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
public abstract class AppException extends RuntimeException{
    private int errorCode;
    private String errorMessage;
    private HttpStatus httpStatus;
//    private Object externalData;

    public AppException(int errorCode, String errorMessage, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.httpStatus = httpStatus;
    }
}
