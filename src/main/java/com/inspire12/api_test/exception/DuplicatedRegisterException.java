package com.inspire12.api_test.exception;

import org.springframework.http.HttpStatus;

public class DuplicatedRegisterException extends AppException{
    public DuplicatedRegisterException() {
        super(11, "this api name is duplicated", HttpStatus.BAD_REQUEST);
    }
}
