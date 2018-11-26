package com.epam.service.advice;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ApiErrors {

    private List<ApiError> apiErrors = new ArrayList<>();

    public List<ApiError> addError(ApiError apiError) {
        apiErrors.add(apiError);

        return apiErrors;
    }
}
