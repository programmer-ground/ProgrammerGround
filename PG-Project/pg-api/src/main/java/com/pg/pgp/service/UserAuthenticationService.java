package com.pg.pgp.service;

import com.pg.pgp.auth.MyUserDetails;
import com.pg.pgp.auth.jwt.JwtAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 인증된 사용자 정보 가져오기 헬퍼
 */
public class UserAuthenticationService {
    public static Long getUserId() {
        return getUserDetails().getId();
    }
    public static MyUserDetails getUserDetails() {
        return ((MyUserDetails) ((JwtAuthenticationToken)SecurityContextHolder.getContext().getAuthentication()).getPrincipal());
    }
}
