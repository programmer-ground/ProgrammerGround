package com.pg.programmerground.jwt;

import com.pg.programmerground.config.MyUserDetails;
import com.pg.programmerground.exception.JwtExpiredException;
import com.pg.programmerground.repository.UserRepository;
import com.pg.programmerground.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * 인증 처리 로직 담은 Provider
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationProvider implements AuthenticationProvider {
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //굳이 체크할 이유가 있나 getOAuthId를 통해 expire를 exception 해줄텐데
        //가져오는 과정에서 만료시간, 조작 Exception 체크
        //Authentication을 만드는 이유는 이 Authentication을 가지고 Controller에서 유저 정보를 가져와 DB데이터를 가져오기 위해 -> DB에서 쓸만한 데이터를 넣어라 UserDetails에
        //DB에서 데이터를 가져와서 비교해야함 -> 굳이? JWT가 애초에 stateless
        try {
            String jwtToken = (String) authentication.getCredentials();
            Long OAuthId = jwtTokenProvider.getOAuthId(jwtToken);
            UserDetails userDetails = userService.loadUserByOAuthId(OAuthId);
            return new JwtAuthenticationToken(userDetails, jwtToken, userDetails.getAuthorities());
        } catch (ExpiredJwtException e) {
            throw new JwtExpiredException("토큰 만료");
        } catch (Exception e) {
            throw new BadCredentialsException("토큰 불량");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
