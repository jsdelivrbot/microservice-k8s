package com.epam.service.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends ApiException {

    public BadRequestException(HttpStatus httpStatus, int code, String msg) {
        super(httpStatus, code, msg);
    }

    public BadRequestException(String message) {
        super(HttpStatus.BAD_REQUEST, 400, message);
    }
}
