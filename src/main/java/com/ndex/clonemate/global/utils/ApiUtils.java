package com.ndex.clonemate.global.utils;

import com.ndex.clonemate.global.dto.ApiResult;

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
