package com.pg.programmerground.util;

import com.pg.programmerground.dto.GithubRepoDto;
import com.pg.programmerground.dto.GithubUserInfoDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;

public class RestApiManager {
    private HttpComponentsClientHttpRequestFactory factory;
    private RestTemplate restTemplate;
    private HttpHeaders header;
    private HttpEntity<?> entity;

    public RestApiManager() {
        this.factory = new HttpComponentsClientHttpRequestFactory();
        this.factory.setReadTimeout(5000);
        this.factory.setConnectionRequestTimeout(5000);
        this.restTemplate = new RestTemplate(this.factory);
        this.header = new HttpHeaders();
        this.entity = new HttpEntity<>(this.header);

    }

    /**
     * Github Api에서 받아온 유저 정보
     * Dto 참고
     */
    public GithubUserInfoDto getGithubUserInfo(String oAuthAccessToken) {
        HttpHeaders headers = new HttpHeaders();
        header.add("Authorization", "token " + oAuthAccessToken);
        return restTemplate.exchange("https://api.github.com/users", HttpMethod.GET, new HttpEntity<>(headers), GithubUserInfoDto.class).getBody();
    };

    public Long getCommitCount(String owner) throws Exception{
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
