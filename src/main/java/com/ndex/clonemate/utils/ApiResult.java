package com.ndex.clonemate.utils;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class ApiResult<T> {
    private final boolean success;
    private final T data;
    private final String errorMessage;
}