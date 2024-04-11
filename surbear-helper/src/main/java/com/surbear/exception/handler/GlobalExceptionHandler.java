package com.surbear.exception.handler;

import com.surbear.exception.ProcessErrorCodeException;
import com.surbear.exception.dto.ErrorResponse;
import com.surbear.exception.errorcode.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProcessErrorCodeException.class)
    public ResponseEntity<Object> handleCustomException(ProcessErrorCodeException exception, HttpServletRequest request) {

        String errorMessage = getErrorMessage(exception);
        String errorLocation = getErrorLocation(exception);

        logger.warn(String.format("BaseException occurred: %s , Location: %s", errorMessage, errorLocation));

        ErrorCode errorCode = exception.getErrorCode();
        return handleExceptionInternal(errorCode, request);
    }


    private ResponseEntity<Object> handleExceptionInternal(ErrorCode errorCode, HttpServletRequest request) {
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(makeErrorResponse(errorCode, request));
    }

    private ErrorResponse makeErrorResponse(ErrorCode errorCode, HttpServletRequest request) {
        return ErrorResponse.builder()
                .status(errorCode.getHttpStatus().value())
                .error(errorCode.name())
                .message(errorCode.getMessage())
                .path(request.getRequestURI())
                .build();
    }

    private String getErrorMessage(Exception exception) {
        return exception.getMessage() != null ? exception.getMessage() : "UNKNOWN_ERROR";
    }

    private String getErrorLocation(Exception exception) {
        if (exception.getStackTrace() == null) {
            return null;
        }
        return exception.getStackTrace()[0].toString();
    }
}