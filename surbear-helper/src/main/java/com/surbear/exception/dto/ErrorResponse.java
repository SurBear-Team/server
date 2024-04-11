package com.surbear.exception.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {

    private final Integer status;
    private final String error;
    private final String message;
    private final String path;

}
