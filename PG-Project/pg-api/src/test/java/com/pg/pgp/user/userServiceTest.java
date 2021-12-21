package com.pg.pgp.user;

import com.pg.pgp.TestUserManagement;
import com.pg.pgp.auth.jwt.JwtAuthenticationToken;
import com.pg.pgp.domain.OAuthUser;
import com.pg.pgp.dto.user.api_req.ReviseUserApi;
import com.pg.pgp.model.OAuthUserRepository;
import com.pg.pgp.playground.TestJsonPackage;
import com.pg.pgp.service.OAuthUserService;
import com.pg.pgp.service.UserAuthenticationService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("jeawoo-local")     //테스트시 profile을 자기껄로 설정
public class userServiceTest {
    private static final Long GITHUB_ID = 1234L;
    private TestJsonPackage dtoList;
    @Autowired
    private OAuthUserService oAuthUserService;
    @Autowired
    TestUserManagement management;
    @Autowired
    OAuthUserRepository oAuthUserRepository;

    /**
     * *@BeforeAll 한번 실행되고 각 테스트 케이스마다 @BeforeEach 실행
     */
    @BeforeAll
    void dataSetUp() throws IOException {
        dtoList = TestJsonPackage.of();
        management.saveTestUser();
    }

    @BeforeEach
    void authenticationSetUp() {
        //Github 로그인을 한번 시도하고
        UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID);
        Authentication authentication = new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    @AfterAll
    void deleteTestUser() {
        management.deleteTestUser();
    }

    @Test
    @Transactional
    void User_정보_수정() {
        //given
        String reviseUserName = "Test";
        OAuthUser user = oAuthUserRepository.findById(UserAuthenticationService.getUserId()).orElseThrow();
        ReviseUserApi reviseUserApi = new ReviseUserApi();
        reviseUserApi.setUserName(reviseUserName);

        //then
        user.updateUser(reviseUserApi);

        //then
        OAuthUser thenUser = oAuthUserRepository.findById(user.getId()).orElseThrow();
        assertEquals(reviseUserName, thenUser.getUserName());
    }
}
