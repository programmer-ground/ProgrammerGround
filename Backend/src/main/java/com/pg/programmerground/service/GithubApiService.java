package com.pg.programmerground.service;

import com.nimbusds.openid.connect.sdk.validators.AccessTokenValidator;
import com.pg.programmerground.dto.GithubTotalDto;
import com.pg.programmerground.util.RestApiManager;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

import javax.persistence.Access;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GithubApiService {
    private final RestApiManager restApiManager;

    public GithubTotalDto getGithubTotalData() throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        DefaultOAuth2User user = (DefaultOAuth2User) ((OAuth2AuthenticationToken)authentication).getPrincipal();
        return GithubTotalDto.builder()
                .totalRepo(getRepositoryCount(user))
                .totalCommit(restApiManager.getCommitCount((String) user.getAttributes().get("login")))
                .build();

    }

    private Long getRepositoryCount(DefaultOAuth2User user) {
        return (Long)user.getAttributes().get("public_repos");
    }

}
