package com.pg.auth.controller;

import com.pg.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    //http://localhost:8080/oauth2/authorization/github
    @GetMapping("/getToken")
    public ResponseEntity<String> index(Authentication authentication) {
        return ResponseEntity.ok().header("token", userService.createJwtToken()).body("success login");
    }

}
