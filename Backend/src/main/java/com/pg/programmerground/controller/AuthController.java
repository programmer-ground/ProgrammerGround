package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.GithubTotalDto;
import com.pg.programmerground.service.GithubRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final GithubRestService githubRestService;

    /*@GetMapping("/auth")
    public Authentication auth(Authentication authentication) {
        return authentication;
    }

    @GetMapping("/adminss")
    public GithubUserInfoDto admin() {
        return githubApiService.getGithubUserInfo();
    }*/

    @GetMapping("/userInfo")
    public ResponseEntity<ApiResponse<GithubTotalDto>> userInfo() throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(githubRestService.getGithubTotalData()));
    }


}
