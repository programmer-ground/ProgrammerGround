package com.pg.auth.jwtConfig;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Component
public class JwtTokenProvider {
    @Value("${spring.security.jwt-token.secret-key:secret-key}")
    private String secretKey;

    //@Value는 인스턴스가 생성된 후에 주입된다. 즉, 생성자 실행후 주입 그러므로 생성자에서는 secerKey는 nullㄱ밧
    @PostConstruct
    public void init(){ // method name doesn't matter
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    //JWT 토큰 생성
    public String createToken(String accessToken, Long OAuthId, List<String> roles) {
        Claims claims = Jwts.claims();
        claims.put("oauthId", OAuthId);
        claims.put("accessToken", accessToken);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 3600000))   //한 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }
}
