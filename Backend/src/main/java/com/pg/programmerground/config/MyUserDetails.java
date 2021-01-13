package com.pg.programmerground.config;

import com.pg.programmerground.entity.User;
import lombok.Getter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserDetails implements UserDetails {
    private Long id;
    private Long oAuthId;
    private String oAuthName;
    private String userName;
    private List<? extends GrantedAuthority> authorities;

    public MyUserDetails(User user) {
        this.id = user.getId();
        this.oAuthId = user.getOauth2AuthorizedClient().getId();
        this.oAuthName = user.getOAuthName();
        this.userName = user.getUserName();
        this.authorities = makeAuthority(user.getRole());
    }
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

    private List<? extends GrantedAuthority> makeAuthority(String role) {
        // "," 구분해서 리스트에 넣음
        return Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
