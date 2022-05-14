package com.ndex.clonemate.certificate.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndex.clonemate.certificate.handler.dto.LoginResponseDto;
import com.ndex.clonemate.global.dto.ApiResult;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class LoginFailureHandler implements AuthenticationFailureHandler {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException {

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        String errorMessage = "로그인 실패";
        if (exception instanceof BadCredentialsException) {
            errorMessage = "Invalid Username / Password";
        } else if (exception instanceof DisabledException) {
            errorMessage = "Locked";
        } else if (exception instanceof CredentialsExpiredException) {
            errorMessage = "Expired password";
        }

        LoginResponseDto responseDto = new LoginResponseDto();
        ApiResult<LoginResponseDto> apiResult = ApiResult.<LoginResponseDto>builder()
                .success(false)
                .errorMessage(errorMessage)
                .data(responseDto)
                .build();

        objectMapper.writeValue(response.getWriter(), apiResult);
    }
}
