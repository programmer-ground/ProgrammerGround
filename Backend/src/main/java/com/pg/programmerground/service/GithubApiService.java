package com.pg.programmerground.service;

import com.pg.programmerground.config.MyUserDetails;
import com.pg.programmerground.dto.GithubUserInfoDto;
import com.pg.programmerground.entity.Oauth2AuthorizedClient;
import com.pg.programmerground.jwt.JwtAuthenticationToken;
import com.pg.programmerground.util.RestApiManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GithubApiService {
    private final RestApiManager restApiManager;
    private final UserService userService;

    @Autowired
    public GithubApiService(UserService userService) {
        this.userService = userService;
        this.restApiManager = new RestApiManager();
    }
    /*public GithubTotalDto getGithubTotalData() throws Exception {
        GithubUserInfoDto userInfo = getGithubUserInfo();
        return GithubTotalDto.builder()
                .totalRepo(getRepositoryCount(user))
                //.totalCommit(restApiManager.getCommitCount((String) user.getAttributes().get("login")))
                .build();

    }*/

    public GithubUserInfoDto getGithubUserInfo() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Oauth2AuthorizedClient oauthUser = userService.findUserById(userDetails.getId()).getOauth2AuthorizedClient();

        return restApiManager.getGithubUserInfo(oauthUser.getAccessTokenValue());
    }

    private Long getRepositoryCount(DefaultOAuth2User user) {
        return (Long) user.getAttributes().get("public_repos");
    }

}
