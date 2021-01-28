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
import java.util.*;

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
        MyUserDetails userDetails = (MyUserDetails) ((JwtAuthenticationToken) SecurityContextHolder.getContext().getAuthentication()).getPrincipal();
        Oauth2AuthorizedClient oauthUser = oAuthMemberRepository.findById(userDetails.getId()).orElseThrow().getOauth2AuthorizedClient();

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "bearer " + oauthUser.getAccessTokenValue());
        GithubOAuthInfoDto oauthInfo = githubRestService.rest("https://api.github.com/user", HttpMethod.GET, header, GithubOAuthInfoDto.class);
        List<GithubRepoDto> githubRepoDto = getOAuthMemberRepository(oauthInfo.getLogin(), header);
        return GithubTotalDto.builder()
                .owner(oauthInfo.getLogin())
                .totalRepo(oauthInfo.getPublicRepos())
                .totalPullRequest(getOAuthPullRequestCount(oauthInfo.getLogin(), header))
                .totalCommit(getOAuthCommitCount(oauthInfo.getLogin(), header))
                .totalLanguage(getMostLanguage(githubRepoDto))
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
     * Github PR, Issue 개수 가져오기
     */
    private Integer getOAuthPullRequestCount(String owner, HttpHeaders header) {
        header.set("Accept", "application/vnd.github.v3+json");
        return (Integer) githubRestService.rest("https://api.github.com/search/issues?q=" + owner, HttpMethod.GET, header, Map.class).get("total_count");
    }

    /**
     * 사용자 Repo 리스트
     * 어디에 사용할지 까먹음
     */
    private List<GithubRepoDto> getOAuthMemberRepository(String owner, HttpHeaders header) {
        int pageNum = 1;
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl("https://api.github.com/users/" + owner + "/repos" + "?per_page=100&page=" + pageNum)
                .build();
        return Arrays.asList(githubRestService.rest(uri.toString(), HttpMethod.GET, header, GithubRepoDto[].class));
    }

    /**
     * Repository에서 가장 많이 사용된 상위 3개 언어 추출
     */
    private List<String> getMostLanguage(List<GithubRepoDto> githubRepoDto) {
        Map<String, Integer> languageMap = new HashMap<>();
        //데이터중에서 프로그래밍 언어만 추출
        githubRepoDto.stream().filter(Objects::nonNull)
                .filter(dto -> !dto.equals("null"))
                .map(GithubRepoDto::getLanguage)
                .forEach(s -> {
                    if(languageMap.containsKey(s)) {
                        languageMap.put(s, languageMap.get(s) + 1);
                    } else {
                        languageMap.put(s, 1);
                    }
                });

        //Map 정렬
        List<Map.Entry<String, Integer>> entries = new ArrayList<Map.Entry<String, Integer>>(languageMap.entrySet());
        List<String> languageList = new ArrayList<>();
        entries.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        //상위 3개만 뽑음
        int cnt = 0;
        for(Map.Entry<String, Integer> entry : entries) {
            languageList.add(entry.getKey());
            if(++cnt == 3) {
                break;
            }
        }
        return languageList;
    }
}

