package com.pg.auth.controller;

import com.pg.auth.dto.JwtLoginDTO;
import com.pg.auth.dto.JwtToken;
import com.pg.auth.exception.InvalidCodeException;
import com.pg.auth.jwtConfig.JwtTokenProvider;
import com.pg.auth.service.OAuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    //추후 DOMAIN 설정 후 수정
    private static final String COOKIE_DOMAIN = ".localhost";
    private final OAuthUserService oAuthUserService;
    private final JwtTokenProvider jwtTokenProvider;
    //http://localhost:8080/oauth2/authorization/github
    /**
     * 프론트용 테스트
     * 프론트에서 구현해야함
     */
    @GetMapping("/loginCode")
    public ResponseEntity<String> code(@RequestParam(name = "code") String code,
                                       @RequestParam(name = "oauthId") Long id) {
        return new ResponseEntity<>("code OK", HttpStatus.OK);
    }

    /**
     * 프론트에서 호출하여 code, oauthId를 통해 유저 인증을 하고 JWT 발급
     */
    @PostMapping("/jwtLogin")
    public ResponseEntity<String> login(@RequestBody JwtLoginDTO jwtLoginDTO, HttpServletResponse response) throws InvalidCodeException {
        JwtToken tokens = oAuthUserService.jwtLogin(jwtLoginDTO.getCode(), jwtLoginDTO.getOauthId());
        response.setHeader("access_token", tokens.getAccessToken());
        response.addHeader("Set-Cookie", "refresh_token=" + tokens.getAccessToken() + "; HttpOnly; Max-Age=1209600; SameSite=Lax");
        return ResponseEntity.ok().body("login");
    }

    /**
     * AccessToken 만료시 RefreshToken 체크후 재발급
     */
    @PostMapping("/reissued")
    public ResponseEntity<String> reissuedAccessToken(@CookieValue("refresh_token") String refreshToken, HttpServletResponse response) {
        response.setHeader("access_token", oAuthUserService.reissuedAccessToken(refreshToken));
        return ResponseEntity.ok().body("success issue accessToken");
    }

    @GetMapping("/info")
    public ResponseEntity<Object> info(Authentication authentication) {
        return ResponseEntity.ok().body(authentication.getPrincipal());
    }

    /**
     * 에러 페이지
     */
    @GetMapping("/err")
    public ResponseEntity<String> error() {
        return new ResponseEntity<>("에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
