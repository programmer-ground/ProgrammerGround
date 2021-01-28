package com.pg.programmerground.controller;

import com.pg.programmerground.controller.response.ApiResponse;
import com.pg.programmerground.dto.GithubTotalDto;
import com.pg.programmerground.service.OAuthMemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/oauth-member")
public class OAuthMemberController {
    private final OAuthMemberService oAuthMemberService;

    @GetMapping("/member")
    public ResponseEntity<ApiResponse<GithubTotalDto>> memberInfo() throws Exception {
        return ResponseEntity.ok().body(new ApiResponse<>(oAuthMemberService.getGithubTotal()));
    }

}
