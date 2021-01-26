package com.pg.programmerground.service;

import com.pg.programmerground.auth.MyUserDetails;
import com.pg.programmerground.dto.GithubRepoDto;
import com.pg.programmerground.dto.GithubTotalDto;
import com.pg.programmerground.dto.GithubUserInfoDto;
import com.pg.programmerground.domain.Oauth2AuthorizedClient;
import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

@Service
@RequiredArgsConstructor
public class GithubApiService {
    private final UserService userService;

    private final HttpComponentsClientHttpRequestFactory factory;
    private final RestTemplate restTemplate;

    @Autowired
    public GithubApiService(UserService userService) {
        this.factory = new HttpComponentsClientHttpRequestFactory();
        this.factory.setReadTimeout(5000);
        this.factory.setConnectionRequestTimeout(5000);
        this.restTemplate = new RestTemplate(this.factory);
        this.userService = userService;
    }

    public GithubTotalDto getGithubTotalData() throws Exception {
        HttpHeaders header = new HttpHeaders();
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        MyUserDetails userDetails = (MyUserDetails) authentication.getPrincipal();
        Oauth2AuthorizedClient oauthUser = userService.findOAuthMemberById(userDetails.getId()).getOauth2AuthorizedClient();
        header.set("Authorization", "token " + oauthUser.getAccessTokenValue());
        GithubUserInfoDto userInfo = getGithubUserInfo(header);

        return GithubTotalDto.builder()
                .totalRepo(userInfo.getPublicRepos())
                .totalCommit(getCommitCount(userInfo.getLogin(), header))
                .build();

    }

    public GithubUserInfoDto getGithubUserInfo(HttpHeaders header) {
        return restTemplate.exchange("https://api.github.com/user", HttpMethod.GET, new HttpEntity<>(header), GithubUserInfoDto.class).getBody();
    }

    /**
     * 유저의 총 Commit 수
     */
    private Long getCommitCount(String owner, HttpHeaders header) throws Exception {
        Map<String, Object> bodyResult = new HashMap<>();
        List<GithubRepoDto> repoList = new ArrayList<>();

        //repoList.
        GithubRepoDto[] repositories = getUserRepository(owner, header);
        return Arrays.stream(Objects.requireNonNull(repositories))
                .map(githubRepoDto -> "https://api.github.com/repos/" + owner + "/" + githubRepoDto.getName() + "/stats/contributors")
                .mapToLong(commitUrl -> {
                    try {

                        Object[] mp = restTemplate.exchange(commitUrl, HttpMethod.GET, new HttpEntity<>(header), Object[].class).getBody();
                        Integer total = (Integer) ((Map)mp[0]).get("total");
                        return total;
                    } catch (RestClientException | NullPointerException ignored) {
                        return 0;
                    }
                })
                .sum();
    }

    /**
     * 사용자 Repo 리스트
     */
    private GithubRepoDto[] getUserRepository(String owner, HttpHeaders header) {
        int pageNum = 1;
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl("https://api.github.com/users/" + owner + "/repos" + "?per_page=100&page=" + pageNum)
                .build();
        return restTemplate.exchange(uri.toString(), HttpMethod.GET, new HttpEntity<>(header), GithubRepoDto[].class).getBody();
    }
}
