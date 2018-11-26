package com.epam.service.exception;

import org.springframework.http.HttpStatus;

public class RestResourceNotFoundException extends ApiException {

    public RestResourceNotFoundException(String msg) {
        super(HttpStatus.NOT_FOUND, 1, msg);
    }

    public RestResourceNotFoundException() {
        super(HttpStatus.NOT_FOUND, 1, "Nincsisilyen entity");
    }
}
