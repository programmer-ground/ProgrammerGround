package com.pg.programmerground.controller;

import com.pg.programmerground.service.GithubRestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth-member")
public class OAuthMemberController {
    private final GithubRestService githubRestService;

    @GetMapping("/member")
    public ResponseEntity<Authentication> memberInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(authentication);
    }

}
