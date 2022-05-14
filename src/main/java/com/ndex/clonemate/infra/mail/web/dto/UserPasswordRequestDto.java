package com.ndex.clonemate.infra.mail.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserPasswordRequestDto {
    long id;
    String originPassword;
    String changePassword;
    String token;

    @Builder
    public UserPasswordRequestDto(long id, String originPassword, String changePassword, String token) {
        this.id = id;
        this.originPassword = originPassword;
        this.changePassword = changePassword;
        this.token = token;
    }
}
