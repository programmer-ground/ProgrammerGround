package com.pg.programmerground.service;

import com.pg.programmerground.config.MyUserDetails;
import com.pg.programmerground.dto.GithubRepoDto;
import com.pg.programmerground.dto.GithubTotalDto;
import com.pg.programmerground.dto.GithubUserInfoDto;
import com.pg.programmerground.entity.Oauth2AuthorizedClient;
import com.pg.programmerground.jwt.JwtAuthenticationToken;
import com.pg.programmerground.util.RestApiManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GithubApiService {
    private final RestApiManager restApiManager;
    private final UserService userService;

    private HttpComponentsClientHttpRequestFactory factory;
    private RestTemplate restTemplate;
    private HttpHeaders header;
    private HttpEntity<?> entity;

    @Autowired
    public GithubApiService(UserService userService) {
        this.factory = new HttpComponentsClientHttpRequestFactory();
        this.factory.setReadTimeout(5000);
        this.factory.setConnectionRequestTimeout(5000);
        this.restTemplate = new RestTemplate(this.factory);
        this.header = new HttpHeaders();
        this.entity = new HttpEntity<>(this.header);
        this.userService = userService;
        this.restApiManager = new RestApiManager();
    }

    public GithubTotalDto getGithubTotalData() throws Exception {
        GithubUserInfoDto userInfo = getGithubUserInfo();
        return GithubTotalDto.builder()
                .totalRepo(userInfo.getPublicRepos())
                .totalCommit(getCommitCount(userInfo.getLogin()))
                .build();

    }

    public GithubUserInfoDto getGithubUserInfo() {
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Oauth2AuthorizedClient oauthUser = userService.findUserById(userDetails.getId()).getOauth2AuthorizedClient();
        HttpHeaders headers = new HttpHeaders();
        header.add("Authorization", "token " + oauthUser.getAccessTokenValue());
        return restTemplate.exchange("https://api.github.com/users", HttpMethod.GET, new HttpEntity<>(headers), GithubUserInfoDto.class).getBody();
    }

    /**
     * 유저의 총 Commit 수
     */
    private Long getCommitCount(String owner) throws Exception {
        int pageNum = 1;
        Map<String, Object> bodyResult = new HashMap<>();
        List<GithubRepoDto> repoList = new ArrayList<>();
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl("https://api.github.com/users/" + owner + "/repos" + "?per_page=100&page=" + pageNum)
                .build();

        ResponseEntity<GithubRepoDto[]> userRepositoryResult = restTemplate.getForEntity(uri.toString(), GithubRepoDto[].class);
        //repoList.
        return Arrays.stream(Objects.requireNonNull(userRepositoryResult.getBody()))
                .map(githubRepoDto -> "https://api.github.com/repos/CJW23/" + githubRepoDto.getName() + "/commits")
                .mapToLong(commitUrl -> Objects.requireNonNull(restTemplate.getForObject(commitUrl, Object[].class)).length)
                .sum();
    }
}
