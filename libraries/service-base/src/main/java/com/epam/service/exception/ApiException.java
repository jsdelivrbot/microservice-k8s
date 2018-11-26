package com.epam.service.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.UUID;

@Getter
public class ApiException extends RuntimeException {

    private UUID uuid;
    private HttpStatus httpStatus;
    private int code;

    public ApiException(HttpStatus httpStatus, int code, String msg) {
        super(msg);
        this.uuid = UUID.randomUUID();
        this.httpStatus = httpStatus;
        this.code = code;
    }
}
