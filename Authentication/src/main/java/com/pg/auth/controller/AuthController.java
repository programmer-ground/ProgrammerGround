package com.pg.auth.controller;

import com.pg.auth.dto.JwtLoginDTO;
import com.pg.auth.exception.InvalidCodeException;
import com.pg.auth.service.OAuthMemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final OAuthMemberService oAuthMemberService;

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
    public ResponseEntity<String> login(@RequestBody JwtLoginDTO jwtLoginDTO,
                                        HttpServletRequest request) throws InvalidCodeException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("token", oAuthMemberService.jwtLogin(jwtLoginDTO.getCode(), jwtLoginDTO.getOauthId()));
        headers.set("Access-Control-Expose-Headers", "token");
        return ResponseEntity.ok().headers(headers).body("login");
    }

    /**
     * 에러 페이지
     */
    @GetMapping("/err")
    public ResponseEntity<String> error() {
        return new ResponseEntity<>("에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
