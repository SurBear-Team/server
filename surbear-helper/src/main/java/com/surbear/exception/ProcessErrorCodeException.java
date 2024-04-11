package com.surbear.exception;

import com.surbear.exception.errorcode.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ProcessErrorCodeException extends RuntimeException {
    private final ErrorCode errorCode;
}
