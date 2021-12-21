package com.pg.pgp;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;

import com.pg.pgp.model.OAuthUserRepository;
import com.pg.pgp.model.Oauth2AuthorizedClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.transaction.annotation.Transactional;

import com.pg.pgp.exceptions.OAuthLoginException;
import com.pg.pgp.jwtConfig.JwtTokenProvider;
import com.pg.pgp.service.RestService;
import com.pg.pgp.service.OAuthUserService;

@ExtendWith(MockitoExtension.class)
@Transactional
public class OAuthUserServiceTest {
    @InjectMocks
    OAuthUserService oAuthUserService;
    @Test
    void OAuthUserService_만들기(@Mock OAuthUserRepository oAuthUserRepository,
                              @Mock Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository,
                              @Mock RestService restService) {
        JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
        OAuthUserService service = new OAuthUserService(oAuthUserRepository, oauth2AuthorizedClientRepository,
                jwtTokenProvider, restService);
        assertNotNull(service);
    }

    @Test
    void OAuth_미인증으로인한_유저_생성_실패(@Mock OAuthUserRepository oAuthUserRepository,
                                @Mock JwtTokenProvider jwtTokenProvider,
                                @Mock RestService restService,
                                @Mock OAuth2AuthenticationToken authentication,
                                @Mock Oauth2AuthorizedClientRepository oAuth2AuthorizedClientRepository
    ) {
        //oauth2_authorized_client 테이블에 없는 유저를 조회할 경우 OAuthLogin 에러
        when(authentication.getName()).thenReturn("1234");
        when(oAuth2AuthorizedClientRepository.findById(1234L)).thenReturn(Optional.empty());
        OAuthUserService oAuthUserService = new OAuthUserService(oAuthUserRepository, oAuth2AuthorizedClientRepository, jwtTokenProvider, restService);
        assertThrows(OAuthLoginException.class, () -> {
            oAuthUserService.createUser(authentication);
        });
    }
}
