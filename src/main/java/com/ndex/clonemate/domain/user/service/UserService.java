package com.ndex.clonemate.domain.user.service;

import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.domain.user.repository.UserRepository;
import com.ndex.clonemate.domain.user.web.dto.UserRegisterRequestDto;
import com.ndex.clonemate.domain.user.web.dto.UserResponseDto;
import com.ndex.clonemate.domain.user.web.dto.UserUpdateRequestDto;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final static String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;

    @Transactional
    public Long register(UserRegisterRequestDto requestDto) {
        User entity = requestDto.toEntity();
        entity.encodePassword(encoder);
        return userRepository.save(entity).getId();
    }

    public UserResponseDto findByUserId(long id) {
        User entity = findUser(id);
        return new UserResponseDto(entity);
    }

    @Transactional
    public void delete(long id) {
        User entity = findUser(id);
        entity.changeDeleteFlag();
    }

    @Transactional
    public void update(long id, UserUpdateRequestDto request) {
        User entity = findUser(id);
        entity.update(request.getUserId(), request.getEmail(), request.getName(),
                request.getIntroText(), request.getEmailSearchYn(), request.getRandomYn());
    }

    private User findUser(long id) {
        return userRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
    }

    public boolean haveUserByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.isPresent();
    }

    public boolean haveUserByAccount(String account) {
        Optional<User> user = userRepository.findByAccount(account);
        return user.isPresent();
    }
}
