package com.pg.programmerground;

import com.pg.programmerground.auth.MyUserDetails;
import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import com.pg.programmerground.domain.OAuthUser;
import com.pg.programmerground.domain.github.Oauth2AuthorizedClient;
import com.pg.programmerground.domain.github.UserGithubInfo;
import com.pg.programmerground.dto.GithubRepoDto;
import com.pg.programmerground.service.OAuthUserService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
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
        System.out.println("webclient : " + (afterTime - beforeTime) / 1000 + "초\n");
    }

    @Test
    void 로그인된_유저_정보_출력() {
        Collection<GrantedAuthority> collection = new ArrayList<>();
        collection.add(new SimpleGrantedAuthority("USER"));
        Oauth2AuthorizedClient oauth2AuthorizedClient = Oauth2AuthorizedClient.builder().id(123L).build();
        UserGithubInfo userGithubInfo = UserGithubInfo.builder().commitCnt(10).pullRequestCnt(10).mostLanguage("awd").repositoryCnt(10).githubPage("awd").profileImg("xx").build();
        OAuthUser oAuthUser = OAuthUser.builder().OAuthName("awd").userName("zxc").Role("USER").oauth2AuthorizedClient(oauth2AuthorizedClient).code("xx").build();
        oAuthUser.setUserGithubInfo(userGithubInfo);
        MyUserDetails userDetails = new MyUserDetails(oAuthUser);
        JwtAuthenticationToken authentication = new JwtAuthenticationToken(userDetails, "principal", collection);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        assertEquals(service.getUserInfo(oAuthUser.getId()).getCommitCnt(), 10);
    }
}
