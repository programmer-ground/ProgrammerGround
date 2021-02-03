package com.pg.programmerground;

import com.pg.programmerground.dto.GithubRepoDto;
import com.pg.programmerground.service.OAuthUserService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.concurrent.ExecutionException;


@SpringBootTest

class ProgrammergroundApplicationTests {

    @Autowired
    OAuthUserService service;

    @Test
    void contextLoads() throws InterruptedException, JSONException, ExecutionException {
        WebClient webClient = WebClient.builder().build();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196");
        //headers.set("Accept", "application/vnd.github.v3+json");
        headers.set("Accept", "application/vnd.github.cloak-preview");
        long beforeTime = System.currentTimeMillis();
        Mono<GithubRepoDto> b = webClient.get().uri("https://api.github.com/search/commits?q=author:CJW23").header("Accept", "application/vnd.github.cloak-preview").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto.class);
        Mono<GithubRepoDto> c = webClient.get().uri("https://api.github.com/search/issues?q=CJW23").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto.class);
        Mono<GithubRepoDto[]> a = webClient.get().uri("https://api.github.com/users/CJW23/repos?per_page=100&page=1").header("Accept", "application/vnd.github.cloak-preview").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto[].class);

        Mono<GithubRepoDto> q = webClient.get().uri("https://api.github.com/search/commits?q=author:CJW23").header("Accept", "application/vnd.github.cloak-preview").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto.class);
        Mono<GithubRepoDto> w = webClient.get().uri("https://api.github.com/search/issues?q=CJW23").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto.class);
        Mono<GithubRepoDto[]> e = webClient.get().uri("https://api.github.com/users/CJW23/repos?per_page=100&page=1").header("Accept", "application/vnd.github.cloak-preview").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto[].class);

        Mono<GithubRepoDto> r = webClient.get().uri("https://api.github.com/search/commits?q=author:CJW23").header("Accept", "application/vnd.github.cloak-preview").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto.class);
        Mono<GithubRepoDto> t = webClient.get().uri("https://api.github.com/search/issues?q=CJW23").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto.class);
        Mono<GithubRepoDto[]> y = webClient.get().uri("https://api.github.com/users/CJW23/repos?per_page=100&page=1").header("Accept", "application/vnd.github.cloak-preview").header("Authorization", "bearer d4bdc274ace2fc36ebb2ddf7bff8de07b57ef196").retrieve().bodyToMono(GithubRepoDto[].class);

        System.out.println(c.block().getTotalCount() + a.block().length + b.block().getTotalCount());
        System.out.println(q.block().getTotalCount() + w.block().getTotalCount() + e.block().length);
        System.out.println(r.block().getTotalCount() + t.block().getTotalCount() + y.block().length);



        long afterTime = System.currentTimeMillis();
        System.out.println("webclient : " + (afterTime - beforeTime)/1000 + "초\n");



    }
}
