package com.ndex.clonemate.infra.mail.service;

import com.ndex.clonemate.certificate.model.RedisRepository;
import com.ndex.clonemate.certificate.model.RedisSession;
import com.ndex.clonemate.infra.mail.web.dto.UserPasswordRequestDto;
import com.ndex.clonemate.domain.user.repository.User;
import com.ndex.clonemate.domain.user.repository.UserRepository;
import com.ndex.clonemate.global.utils.JwtTokenUtils;
import com.ndex.clonemate.global.utils.MailUtils;
import com.ndex.clonemate.global.utils.StringUtils;
import com.ndex.clonemate.global.dto.MailRequestDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import java.time.LocalDateTime;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {
    @Value("${url.reset}")
    private String resetUrl;

    @Value("${url.certify}")
    private String certifyUrl;

    @Value("${jwt.token.issuer}")
    private String issuer;

    @Value("${jwt.token.ttlMillis}")
    private int ttlMillis;

    private static final String ERROR_NO_USER = "[ERROR] 해당 사용자가 없습니다.";
    private static final String ERROR_PASSWORD_INCORRECT = "[ERROR] 비밀번호 오류";
    private static final String URL_FORMAT = "%s&token=%s&id=%d";
    private static final int KEY_LENGTH = 30;

    private final BCryptPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final RedisRepository redisRepository;

    private final MailUtils mailUtils;
    private final JwtTokenUtils tokenUtils;

    public String sendCertifyMail(long id) {
        MailRequestDto mailRequestDTO = MailRequestDto.builder()
                .email(findUser(id).getEmail())
                .build();

        String key = StringUtils.generateRandomString(KEY_LENGTH);
        RedisSession redisSession = new RedisSession(id, key);
        redisRepository.save(redisSession);

        String token = tokenUtils.createToken(String.valueOf(id), issuer, key, ttlMillis);
        mailRequestDTO.writeResetPasswordMail(String.format(URL_FORMAT, certifyUrl, token, id));
        mailUtils.send(mailRequestDTO);

        return token;
    }

    @Transactional
    public void checkCertifyMail(long id, String token) {
        validationToken(id, token);

        User entity = findUser(id);
        entity.changeCertifyFlag();
    }

    @Transactional
    public String sendResetPasswordMail(long id) {
        MailRequestDto mailRequestDTO = new MailRequestDto();

        String key = StringUtils.generateRandomString(KEY_LENGTH);

        RedisSession redisSession = findRedis(id);
        redisSession.setKey(key);
        redisSession.setUpdatedAt(LocalDateTime.now());

        String token = tokenUtils.createToken(String.valueOf(id), issuer, key, ttlMillis);
        mailRequestDTO.writeResetPasswordMail(String.format(URL_FORMAT, resetUrl, token, id));
        mailUtils.send(mailRequestDTO);

        return token;
    }

    @Transactional
    public void changePassword(UserPasswordRequestDto requestDto) {
        validationToken(requestDto.getId(), requestDto.getToken());

        User entity = findUser(requestDto.getId());
        String encodeInputPassword = encoder.encode(requestDto.getOriginPassword());

        if (!entity.getPassword().equals(encodeInputPassword)) {
            throw new IllegalArgumentException(ERROR_PASSWORD_INCORRECT);
        }

        entity.changePassword(requestDto.getOriginPassword());
        entity.encodePassword(encoder);
    }

    public RedisSession findRedis(long id) {
        return redisRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("redis에 존재하지 않는 사용자입니다."));
    }

    private User findUser(long id) {
        return userRepository.findByUserId(id)
                .orElseThrow(() -> new IllegalArgumentException(ERROR_NO_USER));
    }

    private void validationToken(long id, String token) {
        RedisSession redisSession = findRedis(id);
        String key = redisSession.getKey();

        Jws<Claims> jwsToken = tokenUtils.parseJwtToken(token);
        if (tokenUtils.isValidExpiration(jwsToken) && !key.equals(jwsToken.getBody().getSubject())) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 토큰입니다.");
        }
    }
}
