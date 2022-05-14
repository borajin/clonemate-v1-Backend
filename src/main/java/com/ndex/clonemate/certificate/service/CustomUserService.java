package com.ndex.clonemate.certificate.service;

import com.ndex.clonemate.certificate.model.CustomUserDetails;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserService implements UserDetailsService {
    private final static String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";
    private final static String ERROR_NO_ACCOUNT = "[ERROR] 계정 정보를 불러올 수 없습니다.";
    private final UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String account) throws UsernameNotFoundException {
        if(account == null) {
            throw new IllegalArgumentException(ERROR_NO_ACCOUNT);
        }
        User user = userRepository.findByAccount(account)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));

        return new CustomUserDetails(user);
    }
}
