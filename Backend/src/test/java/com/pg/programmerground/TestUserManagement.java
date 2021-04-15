package com.pg.programmerground;

import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import com.pg.programmerground.domain.github.UserGithubInfo;
import com.pg.programmerground.model.OAuthUserRepository;
import com.pg.programmerground.model.Oauth2AuthorizedClientRepository;
import com.pg.programmerground.model.UserGithubInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class TestUserManagement {
    private static final Long GITHUB_ID = 1234L;
    private static final Long GITHUB_ID2 = 12345L;
    @Autowired
    private OAuthUserRepository oAuthUserRepository;
    @Autowired
    private Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    @Autowired
    private UserGithubInfoRepository userGithubInfoRepository;

    @Transactional
    public void saveTestUser() {
        Oauth2AuthorizedClient oauth2AuthorizedClient = Oauth2AuthorizedClient.builder()
                .id(GITHUB_ID)
                .clientRegistrationId("github")
                .accessTokenScopes("read:user")
                .accessTokenType("Bearer")
                .build();
        UserGithubInfo userGithubInfo = UserGithubInfo.builder()
                .commitCnt(0)
                .pullRequestCnt(0)
                .repositoryCnt(0)
                .build();
        OAuthUser oAuthUser = OAuthUser.builder()
                .userName("test")
                .OAuthName("test")
                .Role("ROLE_USER,SCOPE_read:user")
                .oauth2AuthorizedClient(oauth2AuthorizedClient)
                .userGithubInfo(userGithubInfo)
                .build();

        userGithubInfoRepository.save(userGithubInfo);
        oauth2AuthorizedClientRepository.save(oauth2AuthorizedClient);
        oAuthUserRepository.save(oAuthUser);

        Oauth2AuthorizedClient oauth2AuthorizedClient2 = Oauth2AuthorizedClient.builder()
                .id(GITHUB_ID2)
                .clientRegistrationId("github")
                .accessTokenScopes("read:user")
                .accessTokenType("Bearer")
                .build();
        UserGithubInfo userGithubInfo2 = UserGithubInfo.builder()
                .commitCnt(0)
                .pullRequestCnt(0)
                .repositoryCnt(0)
                .build();
        OAuthUser oAuthUser2 = OAuthUser.builder()
                .userName("test")
                .OAuthName("test")
                .Role("ROLE_USER,SCOPE_read:user")
                .oauth2AuthorizedClient(oauth2AuthorizedClient2)
                .userGithubInfo(userGithubInfo2)
                .build();

        userGithubInfoRepository.save(userGithubInfo2);
        oauth2AuthorizedClientRepository.save(oauth2AuthorizedClient2);
        oAuthUserRepository.save(oAuthUser2);
    }

    @Transactional
    public void deleteTestUser() {
        oAuthUserRepository.delete(oauth2AuthorizedClientRepository.findById(1234L).orElseThrow().getUser());
    }

}
