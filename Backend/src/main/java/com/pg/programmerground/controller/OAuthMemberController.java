package com.pg.programmerground.controller;

import com.pg.programmerground.domain.OAuthMember;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/oauth-member")
public class OAuthMemberController {

    @GetMapping("/member")
    public ResponseEntity<Authentication> memberInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok().body(authentication);
    }

}
