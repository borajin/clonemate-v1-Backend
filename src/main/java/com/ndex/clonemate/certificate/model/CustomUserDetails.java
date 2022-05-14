package com.ndex.clonemate.certificate.model;

import com.ndex.clonemate.domain.user.repository.User;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        super();
        this.user = user;
    }

    public long getId() {
        return user.getId();
    }

    public String getEmail() {
        return user.getEmail();
    }

    public String getAccount() {
        return user.getAccount();
    }

    public boolean isNotCertified() { return user.isNotCertifiedUser(); }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.isNotDeletedUser();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }
}