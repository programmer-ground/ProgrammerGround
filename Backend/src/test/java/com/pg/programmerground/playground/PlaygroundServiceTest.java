package com.pg.programmerground.playground;

import java.io.IOException;
import java.util.List;

import com.pg.programmerground.auth.MyUserDetails;
import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.dto.playground.api_req.PositionApi;
import com.pg.programmerground.dto.playground.api_req.PositionLanguageApi;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.service.OAuthUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@SpringBootTest
public class PlaygroundServiceTest {
    private static Long GITHUB_ID = 32676275L;
    @Autowired
    private OAuthUserRepository oAuthUserRepository;
    @Autowired
    private OAuthUserService oAuthUserService;
    private Authentication authentication;
    private MyUserDetails myUserDetails;

    void createUser() {
        //OAuthUser oAuthUser = oAuthUserRepository.findById(1L).orElseThrow();
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID);
        authentication = new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities());
        myUserDetails = (MyUserDetails) authentication.getPrincipal();
    }

    @Test
    void Playground_생성() throws IOException {
        createUser();
        TestJsonPackage.of();
        System.out.println(myUserDetails.getCommitCnt());
    }
}
