package com.pg.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class GithubRestService {
    private final HttpComponentsClientHttpRequestFactory factory;
    private final RestTemplate restTemplate;

    @Autowired
    public GithubRestService() {
        this.factory = new HttpComponentsClientHttpRequestFactory();
        this.factory.setReadTimeout(5000);
        this.factory.setConnectionRequestTimeout(5000);
        this.restTemplate = new RestTemplate(this.factory);
    }

    /**
     * Rest 통신
     */
    public <T> T rest(String url, HttpMethod method, HttpHeaders header, Class<T> response) {
        return restTemplate.exchange(url, method, new HttpEntity<>(header), response).getBody();
    }

}
