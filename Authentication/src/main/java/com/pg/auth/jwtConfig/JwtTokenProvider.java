package com.pg.auth.jwtConfig;

import com.pg.auth.dto.JwtToken;
import com.pg.auth.exception.JwtNotFoundException;
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
    private static final String REFRESH_TOKEN = "refreshToken";
    @Value("${spring.security.jwt-token.secret-key:secret-key}")
    private String secretKey;

    //@Value는 인스턴스가 생성된 후에 주입된다. 즉, 생성자 실행후 주입 그러므로 생성자에서는 secretKey는 null값
    @PostConstruct
    public void init(){ // method name doesn't matter
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * 로그인시 AccessToken, RefreshToken 토큰 생성
     */
    public JwtToken createTokens(String oAuthToken, Long OAuthId, Long userId, List<String> roles) {
        return new JwtToken(
                makeAccessToken(oAuthToken, OAuthId, userId, roles),
                makeRefreshToken(oAuthToken,OAuthId, userId, roles));
    }

    /**
     * AccessToken 생성
     */
    public String createAccessToken(String oAuthToken, Long OAuthId, Long userId, List<String> roles) {
        return makeAccessToken(oAuthToken, OAuthId, userId, roles);
    }

    /**
     * accessToken 생성
     */
    private String makeAccessToken(String oAuthToken, Long OAuthId, Long userId, List<String> roles) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("oauthId", OAuthId);
        claims.put("accessToken", oAuthToken);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "accessToken")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 60000))   //10초
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * Test AccessToken 발급
     */
    public String makeTestAccessToken(String oAuthToken, Long OAuthId, Long userId, List<String> roles) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("oauthId", OAuthId);
        claims.put("accessToken", oAuthToken);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "accessToken")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 600000000))   //10초
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * refreshToken 생성
     */
    private String makeRefreshToken(String oAuthToken, Long OAuthId, Long userId, List<String> roles) {
        Claims claims = Jwts.claims();
        claims.put("userId", userId);
        claims.put("oauthId", OAuthId);
        claims.put("accessToken", oAuthToken);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setHeaderParam("type", "refreshToken")
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + 1209600000))   //2주
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    private Claims getBody(String token) {
        return validateToken(token).getBody();
    }

    private JwsHeader getHeader(String token) {
        return validateToken(token).getHeader();
    }

    /**
     * token 추출
     */
    public String resolveToken(String refreshToken) {
        return refreshToken.replace("Bearer", "").trim();
    }

    /**
     * OAuthId 추출
     */
    public Long getOAuthIdByRefreshToken(String jwtToken) {
        String refreshToken = this.resolveToken(jwtToken);
        JwsHeader header = this.getHeader(refreshToken);
        Claims claims = this.getBody(refreshToken);
        return header.get("type").equals(REFRESH_TOKEN) ?
                Long.valueOf((Integer) claims.get("oauthId")) : null;
    }

    /**
     * 토큰 복호화
     * 이 과정을 통해 Expire, Invalid 체크 후 Exception 던짐
     */
    private Jws<Claims> validateToken(String jwtToken) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
    }
}
