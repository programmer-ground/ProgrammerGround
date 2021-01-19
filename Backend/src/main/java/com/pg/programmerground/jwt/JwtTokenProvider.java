package com.pg.programmerground.jwt;

import com.pg.programmerground.exception.JwtNotFoundException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    @Value("${spring.security.jwt-token.secret-key:secret-key}")
    private String secretKey;

    //@Value는 인스턴스가 생성된 후에 주입된다. 즉, 생성자 실행후 주입 그러므로 생성자에서는 secerKey는 nullㄱ밧
    @PostConstruct
    public void init() { // method name doesn't matter
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * 토큰 생성
     */
    public String createToken(String accessToken, Long OAuthId, List<String> roles) {
        Claims claims = Jwts.claims();
        claims.put("oauthId", OAuthId);
        claims.put("accessToken", accessToken);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 36))   //한 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 헤더에서 토큰 추출
     */
    public String resolveToken(HttpServletRequest request) {
        String header = request.getHeader("token");
        if(header == null) {
            throw new JwtNotFoundException("토큰이 존재하지 않음");
        }
        return request.getHeader("token");
    }

    /**
     * OAuthId 추출
     */
    public Long getOAuthId(String jwtToken) {
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
