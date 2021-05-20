package com.pg.programmerground.auth.jwt;

import com.pg.programmerground.exception.JwtNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;

@Component
public class JwtTokenProvider {
    @Value("${spring.security.jwt-token.secret-key:secret-key}")
    private String secretKey;

    //@Value는 인스턴스가 생성된 후에 주입된다. 즉, 생성자 실행후 주입 그러므로 생성자에서는 secerKey는 null값
    @PostConstruct
    public void init() { // method name doesn't matter
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * 헤더에서 토큰 추출
     */
    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("Authorization");
        if(header == null) {
            throw new JwtNotFoundException("토큰이 존재하지 않음");
        }
        return header.replace("Bearer", "").trim();
    }

    /**
     * OAuthId 추출
     */
    public Long getOAuthIdByToken(String jwtToken) {
        return Long.valueOf((Integer) getBody(jwtToken).get("oauthId"));
    }

    /**
     * OAuth AccessToken 추출
     */
    public String getOAuthAccessToken(String jwtToken) {
        return (String) getBody(jwtToken).get("accessToken");
    }

    /**
     * 토큰 복호화
     * 이 과정을 통해 Expire, Invalid 체크 후 Exception 던짐
     */
    private Jws<Claims> validateToken(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
    }

    private Claims getBody(String jwtToken) {
        return validateToken(jwtToken).getBody();
    }
}
