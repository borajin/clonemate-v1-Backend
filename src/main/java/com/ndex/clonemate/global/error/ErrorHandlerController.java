package com.ndex.clonemate.global.error;

import com.ndex.clonemate.global.dto.ApiResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlerController {

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiResult<?> handleException(Exception e) {
        return ApiResult.<Long>builder()
                .success(false)
                .errorMessage(e.getMessage())
                .build();
    }
}
