package com.pg.programmerground.service;

import com.pg.programmerground.auth.MyUserDetails;
import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import com.pg.programmerground.domain.OAuthMember;
import com.pg.programmerground.domain.Oauth2AuthorizedClient;
import com.pg.programmerground.dto.GithubOAuthInfoDto;
import com.pg.programmerground.dto.GithubRepoDto;
import com.pg.programmerground.dto.GithubTotalDto;
import com.pg.programmerground.model.OAuthMemberRepository;
import com.pg.programmerground.model.Oauth2AuthorizedClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuthMemberService {
    private final OAuthMemberRepository oAuthMemberRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    private final GithubRestService githubRestService;

    /**
     * OAuthID를 통해 UserDetails 가져옴
     */
    public UserDetails loadUserByOAuthId(Long OAuthId) {
        //OAuthID를 통해 User정보를 가져온다.
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
                .findById(OAuthId)
                .orElseThrow(() -> new EntityNotFoundException("OAuth 존재하지 않음"));
        OAuthMember oAuthMember = oAuthMemberRepository.findByOauth2AuthorizedClient(authorizedClient);
        return new MyUserDetails(oAuthMember);
    }

    /**
     * Github Total Data
     * repo
     * commit
     * star
     */
    public GithubTotalDto getGithubTotal() {
        //Spring Seucrity UserDetails 객체 가져오기
        MyUserDetails userDetails = (MyUserDetails)((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        Oauth2AuthorizedClient oauthUser = oAuthMemberRepository.findById(userDetails.getId()).orElseThrow().getOauth2AuthorizedClient();

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "token " + oauthUser.getAccessTokenValue());
        GithubOAuthInfoDto oauthInfo = githubRestService.rest("https://api.github.com/user", HttpMethod.GET, header, GithubOAuthInfoDto.class);
        return GithubTotalDto.builder()
                .owner(oauthInfo.getLogin())
                .totalRepo(oauthInfo.getPublicRepos())
                .totalCommit(getOAuthCommitCount(oauthInfo.getLogin(), header))
                .build();
    }

    /**
     * Github Commit 개수 가져오기
     */
    private Integer getOAuthCommitCount(String owner, HttpHeaders header) {
        header.set("Accept", "application/vnd.github.cloak-preview");
        return (Integer) githubRestService.rest("https://api.github.com/search/commits?q=author:" + owner, HttpMethod.GET, header, Map.class).get("total_count");
    }

    /**
     * 사용자 Repo 리스트
     * 어디에 사용할지 까먹음
     */
    private GithubRepoDto[] getUserRepository(String owner, HttpHeaders header) {
        int pageNum = 1;
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl("https://api.github.com/users/" + owner + "/repos" + "?per_page=100&page=" + pageNum)
                .build();
        return githubRestService.rest(uri.toString(), HttpMethod.GET, header, GithubRepoDto[].class);
    }
}

