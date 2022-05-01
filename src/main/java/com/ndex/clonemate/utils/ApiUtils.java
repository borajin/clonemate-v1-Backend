package com.ndex.clonemate.utils;

public class ApiUtils {
    private ApiUtils() {
    }

    public static ApiResult<String> createSuccessEmptyApi() {
        return ApiResult.<String>builder()
                .success(true)
                .data("")
                .build();
    }

    public static ApiResult<Long> createFailedApi(Exception e) {
        return ApiResult.<Long>builder()
                .success(false)
                .errorMessage(e.getMessage())
                .build();
    }
}
