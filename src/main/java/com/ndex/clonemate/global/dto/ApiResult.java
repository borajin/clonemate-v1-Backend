package com.ndex.clonemate.global.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//400(요청 오류)	요청이 잘못되었을 경우
//401(인증실패)	애플리케이션 클라이언트 아이디와 시크릿 값이 없거나 잘못되었을 경우
//403(서버가 허용하지 않는 호출)	필수 요청 변수가 빠졌거나 요청변수 이름이 잘못되었을 경우
//404(API 없음)	API 요청 URL이 잘못되었을 경우
//500(서버오류)	API 호출은 정상적으로 했지만, API 서버 유지보수나 시스템 오류로 인한 에러가 발생하였을 경우

@Setter
@Getter //or @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY))
@Builder
public class ApiResult<T> {
    private final boolean success;
    private final T data;
    private final String errorMessage;
}