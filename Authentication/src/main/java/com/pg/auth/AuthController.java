package com.pg.auth;

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
        return new ResponseEntity<Authentication>(authentication, HttpStatus.OK);
    }

}
