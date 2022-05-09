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

    public static <T> ApiResult<T> createSuccessApi(T t) {
        return ApiResult.<T>builder()
                .success(true)
                .data(t)
                .build();
    }
}
