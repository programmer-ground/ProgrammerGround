package com.pg.auth;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.transaction.annotation.Transactional;

import com.pg.auth.domain.OAuthUser;
import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.jwtConfig.JwtTokenProvider;
import com.pg.auth.repository.OAuthUserRepository;
import com.pg.auth.repository.Oauth2AuthorizedClientRepository;
import com.pg.auth.service.GithubRestService;
import com.pg.auth.service.OAuthUserService;

@ExtendWith(MockitoExtension.class)
@Transactional
public class OAuthUserServiceTest {
    @Test
    void OAuthUserService_만들기(@Mock OAuthUserRepository oAuthUserRepository,
        @Mock Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository,
        @Mock GithubRestService githubRestService) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        OAuthUserService service = new OAuthUserService(oAuthUserRepository, oauth2AuthorizedClientRepository,
            jwtTokenProvider, githubRestService);
        assertNotNull(service);
    }

    @Test
    void OAuth_미인증으로인한_유저_생성_실패(
        @Mock OAuthUserRepository oAuthUserRepository,
        @Mock JwtTokenProvider jwtTokenProvider,
        @Mock GithubRestService githubRestService,
        @Mock OAuth2AuthenticationToken authentication,
        @Mock Oauth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
        ) {
        //oauth2_authorized_client 테이블에 없는 유저를 조회할 경우 OAuthLogin 에러
        when(authentication.getName()).thenReturn("1234");
        when(oAuth2AuthorizedClientRepository.findById(1234L)).thenReturn(Optional.empty());
        OAuthUserService oAuthUserService = new OAuthUserService(oAuthUserRepository, oAuth2AuthorizedClientRepository, jwtTokenProvider, githubRestService);
        assertThrows(OAuthLoginException.class, () -> {
            oAuthUserService.createUser(authentication);
        });
    }
}
