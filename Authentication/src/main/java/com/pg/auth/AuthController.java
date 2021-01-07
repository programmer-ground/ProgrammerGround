package com.pg.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @GetMapping("/")
    public ResponseEntity<Authentication> index(Authentication authentication) {
        return new ResponseEntity<>(authentication, HttpStatus.OK);
    }

}
