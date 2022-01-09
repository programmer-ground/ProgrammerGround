package com.pg.pgp.controller;

import com.pg.pgp.domain.Playground;
import com.pg.pgp.dto.login.JwtLoginDTO;
import com.pg.pgp.authentication.JwtToken;
import com.pg.pgp.exceptions.InvalidCodeException;
import com.pg.pgp.authentication.JwtTokenProvider;
import com.pg.pgp.model.PlaygroundRepository;
import com.pg.pgp.service.OAuthUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    //추후 DOMAIN 설정 후 수정
    private static final String COOKIE_DOMAIN = ".localhost";
    private final OAuthUserService oAuthUserService;
    private final JwtTokenProvider jwtTokenProvider;
    private final PlaygroundRepository playgroundRepository;
    private final Environment env;
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
     * Test Access Token 발급
     * 만료시간 무제한
     */
     @GetMapping("/test-token")
     public String testToken(@RequestParam Long oauthId) throws InvalidCodeException {
         return oAuthUserService.testAccessToken(oauthId);
     }

     @GetMapping("/testest")
     public String testest() {
         Playground playground = playgroundRepository.findById(1L).orElseThrow();
         return playground.getDescription();
     }

    /**
     * 프론트에서 호출하여 code, oauthId를 통해 유저 인증을 하고 JWT 발급
     */
    @PostMapping("/jwtLogin")
    public ResponseEntity<String> login(@RequestBody JwtLoginDTO jwtLoginDTO, HttpServletResponse response) throws InvalidCodeException {
        JwtToken tokens = oAuthUserService.jwtLogin(jwtLoginDTO.getCode(), jwtLoginDTO.getOauthId());
        response.setHeader("Set-Cookie", "access_token=" + tokens.getAccessToken() + "; Domain=; Max-Age=60; SameSite=Lax");
        response.addHeader("Set-Cookie", "refresh_token=" + tokens.getRefreshToken() + "; Domain=; Max-Age=1209600; SameSite=Lax");
        return ResponseEntity.ok().body("login");
    }

    /**
     * AccessToken 만료시 RefreshToken 체크후 재발급
     */
    @PostMapping("/reissued")
    public ResponseEntity<String> reissuedAccessToken(@RequestHeader("Authorization") String refreshToken, HttpServletResponse response) {
        response.setHeader("Set-Cookie","access_token=" + oAuthUserService.reissuedAccessToken(refreshToken) + "; Max-Age=60; SameSite=Lax");
        return ResponseEntity.ok().body("success issue accessToken");
    }

    @GetMapping("/info")
    public ResponseEntity<Object> info(Authentication authentication) {
        return ResponseEntity.ok().body(authentication.getPrincipal());
    }

    @GetMapping("/test")
    public String test() {
        return env.getProperty("test.name");
    }

    /**
     * 에러 페이지
     */
    @GetMapping("/err")
    public ResponseEntity<String> error() {
        return new ResponseEntity<>("에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
