package com.ndex.clonemate.user.service;

import com.ndex.clonemate.user.domain.User;
import com.ndex.clonemate.user.domain.UserRepository;
import com.ndex.clonemate.user.web.dto.UserRegisterRequestDto;
import com.ndex.clonemate.user.web.dto.UserResponseDto;
import com.ndex.clonemate.user.web.dto.UserUpdateRequestDto;
import com.ndex.clonemate.utils.ApiResult;
import com.ndex.clonemate.utils.ApiUtils;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
@Service
public class UserService {
    private final static String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

   @Transactional
    public ResponseEntity<?> register(UserRegisterRequestDto requestDto) {
        try {
            User entity = requestDto.toEntity();
            entity.encodePassword(encoder);
            Long id = userRepository.save(entity).getId();

            ApiResult<Long> apiResult = ApiResult.<Long>builder()
                    .success(true)
                    .data(id)
                    .build();

            return ResponseEntity.ok()
                    .body(apiResult);

        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(ApiUtils.createFailedApi(e));
        }
    }

    public ResponseEntity<?> findByUserId(long id) {
        try {
            User entity = findUser(id);

            ApiResult<UserResponseDto> apiResult = ApiResult.<UserResponseDto>builder()
                    .success(true)
                    .data(new UserResponseDto(entity))
                    .build();

            return ResponseEntity.ok()
                    .body(apiResult);

        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(ApiUtils.createFailedApi(e));
        }
    }

    @Transactional
    public ResponseEntity<?> delete(long id) {
        try {
            User entity = findUser(id);
            entity.changeDeleteFlag();
            return ResponseEntity.ok()
                    .body(ApiUtils.createSuccessEmptyApi());

        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(ApiUtils.createFailedApi(e));
        }
    }

    @Transactional
    public ResponseEntity<?> update(long id, UserUpdateRequestDto request) {
        try {
            User entity = findUser(id);
            entity.update(request.getUserId(), request.getEmail(), request.getName(),
                    request.getIntroText(), request.getEmailSearchYn(), request.getRandomYn());
            return ResponseEntity.ok()
                    .body(ApiUtils.createSuccessEmptyApi());

        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiUtils.createFailedApi(e));
        }
    }

    public ResponseEntity<?> findUserByEmail(String email) {
        try {
            userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
            return ResponseEntity.ok()
                    .body(ApiUtils.createSuccessEmptyApi());
        } catch (Exception e) {
            return ResponseEntity.ok().body(ApiUtils.createFailedApi(e));
        }
    }

    public ResponseEntity<?> findUserByAccount(String account) {
        try {
            userRepository.findByAccount(account)
                    .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
            return ResponseEntity.ok()
                    .body(ApiUtils.createSuccessEmptyApi());
        } catch (Exception e) {
            return ResponseEntity.ok()
                    .body(ApiUtils.createFailedApi(e));
        }
    }

    private User findUser(long id) {
        return userRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
    }
}
