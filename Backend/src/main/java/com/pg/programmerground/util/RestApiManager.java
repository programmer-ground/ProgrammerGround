package com.pg.programmerground.util;

import com.pg.programmerground.dto.GithubRepoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.http.HttpClient;
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

    /*public List<String> getGithubApi() {

    }*/

    public Long getCommitCount(String owner) throws Exception{
        int pageNum = 1;
        Map<String, Object> bodyResult = new HashMap<>();
        List<GithubRepoDTO> repoList = new ArrayList<>();
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl("https://api.github.com/users/" + owner + "/repos" + "?per_page=100&page=" + pageNum)
                .build();


        ResponseEntity<GithubRepoDTO[]> userRepositoryResult = restTemplate.getForEntity(uri.toString(), GithubRepoDTO[].class);
        //repoList.
        return Arrays.stream(Objects.requireNonNull(userRepositoryResult.getBody()))
                .map(githubRepoDTO -> "https://api.github.com/repos/CJW23/" + githubRepoDTO.getName() + "/commits")
                .mapToLong(commitUrl -> Objects.requireNonNull(restTemplate.getForObject(commitUrl, Object[].class)).length)
                .sum();
    }

}
