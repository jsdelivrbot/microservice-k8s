package com.epam.service.advice;

import com.epam.service.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.UUID;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ApiException.class})
    public ResponseEntity<Object> handleApiException(ApiException ex, WebRequest request) {

        LOG.error(String.format("API exception [%s]", ex.getUuid()), ex);

        ApiError apiError = ApiError.builder()
                .statusCode(ex.getHttpStatus())
                .message(ex.getMessage())
                .info(String.format("error ID: %s", ex.getUuid()))
                .build();

        ApiErrors apiErrors = new ApiErrors();
        apiErrors.addError(apiError);

        return super.handleExceptionInternal(ex, apiErrors, null, ex.getHttpStatus(), request);
    }

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex, WebRequest request) {

        UUID uuid = UUID.randomUUID();

        LOG.error(String.format("Server side exception [%s]", uuid), ex);

        ApiError apiError = ApiError.builder()
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(ex.getMessage())
                .info(String.format("error ID: %s", uuid))
                .build();

        ApiErrors apiErrors = new ApiErrors();
        apiErrors.addError(apiError);

        return super.handleExceptionInternal(ex, apiErrors, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        UUID uuid = UUID.randomUUID();

        LOG.error(String.format("Server side exception [%s]", uuid), ex);

        ApiError apiError = ApiError.builder()
                .statusCode(status)
                .message(ex.getMessage())
                .info(String.format("error ID: %s", uuid))
                .build();

        ApiErrors apiErrors = new ApiErrors();
        apiErrors.addError(apiError);

        return super.handleExceptionInternal(ex, apiErrors, headers, status, request);
    }
}
