package com.pg.programmerground.config;

import lombok.Getter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class MyUserDetails implements UserDetails {
    private Long id;
    private Long oAuthId;
    private String oAuthName;
    private String userName;
    private List<? extends GrantedAuthority> authorities;

    public long getId() {
        return this.id;
    }

    public long getOAuthId() {
        return this.oAuthId;
    }

    public String getOAuthName() {
        return this.oAuthName;
    }

    public String getUserName() {
        return this.userName;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
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
        return true;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }
}
