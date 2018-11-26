package com.epam.service.advice;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;

@Value
@Builder
public class ApiError {

    private HttpStatus statusCode;
    private String message;
    private String info;
}
