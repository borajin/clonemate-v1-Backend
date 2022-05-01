package com.ndex.clonemate.certificate.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ndex.clonemate.certificate.filter.dto.CertificateDto;
import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.utils.StringUtils;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

public class CustomAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public CustomAuthenticationFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
        super(requiresAuthenticationRequestMatcher);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException {
        if (!isAjax(request)) {
            throw new IllegalStateException("지원되지 않는 방식의 인증 요청");
        }

        CertificateDto certificateDTO = objectMapper.readValue(request.getReader(), CertificateDto.class);
        String account = certificateDTO.getAccount();
        String password = certificateDTO.getPassword();

        if (StringUtils.isEmpty(account) || StringUtils.isEmpty(password)) {
            throw new IllegalArgumentException("account or password is null");
        }

        return getAuthenticationManager().authenticate(new CustomAuthenticationToken(account, password));
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }
}