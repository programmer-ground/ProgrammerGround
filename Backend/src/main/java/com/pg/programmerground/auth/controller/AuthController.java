package com.pg.programmerground.auth.controller;

import com.pg.programmerground.dto.GithubTotalDto;
import com.pg.programmerground.service.GithubApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final GithubApiService githubApiService;

    /*@GetMapping("/auth")
    public Authentication auth(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/adminss")
    public GithubUserInfoDto admin() {
        return githubApiService.getGithubUserInfo();
    }*/

    @GetMapping("/userInfo")
    public ResponseEntity<GithubTotalDto> userInfo() throws Exception {
        return new ResponseEntity<>(githubApiService.getGithubTotalData(), HttpStatus.OK);
    }


}
