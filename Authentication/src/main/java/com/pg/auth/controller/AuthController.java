package com.pg.auth.controller;

import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final UserService userService;

    //http://localhost:8080/oauth2/authorization/github
    @GetMapping("/getToken")
    public ResponseEntity<String> index(Authentication authentication) throws OAuthLoginException {
        return ResponseEntity.ok().header("token", userService.createJwtToken()).body("success login");
    }

    @GetMapping("/err")
    public ResponseEntity<String> error() {
        return new ResponseEntity<>("에러 발생", HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
