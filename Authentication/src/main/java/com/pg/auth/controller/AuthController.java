package com.pg.auth.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    //http://localhost:8080/oauth2/authorization/github
    @GetMapping("/accessToken")
    public ResponseEntity<Authentication> index(Authentication authentication) {
        //JWT로 만들어서 헤더에 담아 전송해야함.
        return ResponseEntity.ok().header("example", "token").body(authentication);
    }

}
