package com.ndex.clonemate.certificate.provider;

import com.ndex.clonemate.certificate.service.CustomUserService;
import com.ndex.clonemate.certificate.model.CustomAuthenticationToken;
import com.ndex.clonemate.certificate.model.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomAuthenticationProvider implements AuthenticationProvider {
    private final CustomUserService userDetailsService;

    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String account = authentication.getName();
        String password = (String) authentication.getCredentials();

        CustomUserDetails user = userDetailsService.loadUserByUsername(account);

        if (!this.passwordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("패스워드 불일치");
        }

        if (user.isNotCertified()) {
            throw new BadCredentialsException("이메일 인증을 미완료한 사용자입니다.");
        }

        return new CustomAuthenticationToken(account, user.getEmail(), null, user.getId(), user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(CustomAuthenticationToken.class);
    }
}
