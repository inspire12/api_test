package com.inspire12.api_test.exception;

import org.springframework.http.HttpStatus;

public class NotSupportedException extends AppException{
    public NotSupportedException() {
        super(41, "request type not supported", HttpStatus.BAD_REQUEST);
    }
}
