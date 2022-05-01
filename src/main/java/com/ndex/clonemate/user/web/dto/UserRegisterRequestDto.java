package com.ndex.clonemate.user.web.dto;

import com.ndex.clonemate.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRegisterRequestDto {
    private String userId;
    private String email;
    private String name;
    private String password;
    private String introText;

    public User toEntity() {
        return User.builder()
                .name(name)
                .account(userId)
                .email(email)
                .password(password)
                .introText(introText)
                .build();
    }
}
