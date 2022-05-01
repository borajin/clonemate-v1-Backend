package com.ndex.clonemate.certificate.filter.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CertificateDto {
    private long id;
    private String account;
    private String password;

    @Builder
    public CertificateDto(long id, String account, String password) {
        this.id = id;
        this.account = account;
        this.password = password;
    }
}
