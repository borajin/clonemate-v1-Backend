package com.ndex.clonemate.certificate.model;

import java.util.Collection;
import lombok.Getter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

@Getter
public class CustomAuthenticationToken extends AbstractAuthenticationToken {
    private long id;
    private String account;
    private String email;
    private String credentials;

    public CustomAuthenticationToken(String account, String credentials) {
        super(null);
        this.account = account;
        this.credentials = credentials;
    }

    public CustomAuthenticationToken(String account, String email, String credentials, long id,
                                     Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.account = account;
        this.email = email;
        this.credentials = credentials;
        this.id = id;
        super.setAuthenticated(true);
    }

    public CustomAuthenticationToken(Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.account;
    }
}
