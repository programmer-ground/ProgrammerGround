package com.pg.auth.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.pg.auth.domain.OAuthMember;
import com.pg.auth.domain.github.MemberGithubInfo;
import com.pg.auth.domain.github.Oauth2AuthorizedClient;
import com.pg.auth.dto.GithubOAuthInfoDto;
import com.pg.auth.dto.GithubRepoDto;
import com.pg.auth.dto.RepositoryItem;
import com.pg.auth.exception.InvalidCodeException;
import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.jwtConfig.JwtTokenProvider;
import com.pg.auth.repository.Oauth2AuthorizedClientRepository;
import com.pg.auth.repository.OAuthMemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OAuthMemberService {
    private static final int VALID_CODE = 0;
    private final OAuthMemberRepository oAuthMemberRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final GithubRestService githubRestService;

    //jdbc service에서 load하여 가져온다.
    public OAuthMemberService(OAuthMemberRepository oAuthMemberRepository, Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository, JwtTokenProvider jwtTokenProvider, GithubRestService githubRestService) {
        this.oAuthMemberRepository = oAuthMemberRepository;
        this.oauth2AuthorizedClientRepository = oauth2AuthorizedClientRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.githubRestService = githubRestService;
    }

    /**
     * OAuth 인증 성공후 유저 생성
     */
    @Transactional
    public OAuthMember createUser(OAuth2AuthenticationToken authentication) throws OAuthLoginException, ExecutionException, InterruptedException {
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
                .findById(Long.valueOf(authentication.getName())).orElseThrow(() -> new OAuthLoginException("OAuth 로그인 에러"));
        MemberGithubInfo memberGithubInfo;
        OAuthMember oAuthMember = oAuthMemberRepository.findByOauth2AuthorizedClient(authorizedClient);
        //로그인 코드 생성
        UUID loginCode = UUID.randomUUID();

        //신규 유저 체크
        if (oAuthMember == null) {
            OAuth2User oAuth2User = authentication.getPrincipal();
            oAuthMember = OAuthMember.builder()
                    .userName(oAuth2User.getAttribute("name"))
                    .oauth2AuthorizedClient(authorizedClient)
                    .OAuthName(oAuth2User.getAttribute("login"))
                    .code(loginCode.toString())
                    .Role(oAuth2User.getAuthorities().stream().
                            map(GrantedAuthority::getAuthority).
                            collect(Collectors.joining(",")))
                    .build();
            oAuthMemberRepository.save(oAuthMember);
        }
        oAuthMember.updateMemberGithubInfo(getGithubInfo(oAuthMember));
        oAuthMember.updateLoginCode(loginCode.toString());
        return oAuthMember;
    }

    /**
     * OAuth 인증 후 발급된 Code를 확인해 Jwt 발급
     */
    @Transactional
    public String jwtLogin(String code, Long id) throws InvalidCodeException {
        OAuthMember oAuthMember = oAuthMemberRepository.findByCodeAndOauth2AuthorizedClient(code, oauth2AuthorizedClientRepository.findById(id).orElseThrow());
        if (oAuthMember == null || !validateLoginCode(code, oAuthMember)) {
            throw new InvalidCodeException("Login Code 에러");
        }
        oAuthMember.setCode("");   //인증 완료후 code는 지워준다.
        return createJwtToken(oAuthMember);
    }

    /**
     * JWT 토큰 생성
     */
    public String createJwtToken(OAuthMember oAuthMember) {
        return jwtTokenProvider.createToken(
                oAuthMember.getOauth2AuthorizedClient().getAccessTokenValue(),
                oAuthMember.getOauth2AuthorizedClient().getId(),
                oAuthMember.getId(),
                Arrays.stream(oAuthMember.getRole().split(",")).map(String::new).collect(Collectors.toList()));
    }

    /**
     * 코드 검증
     */
    private boolean validateLoginCode(String code, OAuthMember oAuthMember) {
        //유저 id와 code를 비교하여 판별
        return UUID.fromString(code).compareTo(UUID.fromString(oAuthMember.getCode())) == VALID_CODE;
    }

    /**
     * Github Total Data
     * repo
     * commit
     * star
     */
    public MemberGithubInfo getGithubInfo(OAuthMember oAuthMember) throws ExecutionException, InterruptedException {
        //Spring Seucrity UserDetails 객체 가져오기

        Oauth2AuthorizedClient oauthUser = oAuthMember.getOauth2AuthorizedClient();

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "bearer " + oauthUser.getAccessTokenValue());
        GithubOAuthInfoDto oauthInfo = githubRestService.rest(
                "https://api.github.com/user", HttpMethod.GET, header, GithubOAuthInfoDto.class);

        CompletableFuture<GithubRepoDto> repoFuture = CompletableFuture.supplyAsync(() -> getOAuthMemberRepository(oauthInfo.getLogin(), header));
        CompletableFuture<Integer> pullRequestFuture = CompletableFuture.supplyAsync(() -> getOAuthPullRequestCount(oauthInfo.getLogin(), header));
        CompletableFuture<Integer> commitFuture = CompletableFuture.supplyAsync(() -> getOAuthCommitCount(oauthInfo.getLogin(), header));
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(repoFuture, pullRequestFuture, commitFuture);
        voidCompletableFuture.get();

        return MemberGithubInfo.builder()
                //프로필 이미지
                .profileImg(oauthInfo.getAvatarUrl())
                //github 유저 페이지
                .githubPage(oauthInfo.getHtmlUrl())
                //Commit 개수
                .commitCnt(commitFuture.join())
                //Repo 개수
                .repositoryCnt(repoFuture.join().getTotalCount())
                //Issue, PR 수
                .pullRequestCnt(pullRequestFuture.join())
                //가장 많이 사용한 언어 수
                .mostLanguage(getMostLanguage(repoFuture.join().getRepositoryItems()))
                .member(oAuthMember)
                .build();
    }

    /**
     * Github Commit 개수 가져오기
     */
    public Integer getOAuthCommitCount(String owner, HttpHeaders header) {
        header.set("Accept", "application/vnd.github.cloak-preview");
        return (Integer)githubRestService.rest(
                "https://api.github.com/search/commits?q=author:" + owner, HttpMethod.GET, header, Map.class)
                .get("total_count");
    }

    /**
     * Github PR, Issue 개수 가져오기
     */
    private Integer getOAuthPullRequestCount(String owner, HttpHeaders header) {
        header.set("Accept", "application/vnd.github.v3+json");
        return (Integer)githubRestService.rest(
                "https://api.github.com/search/issues?q=" + owner, HttpMethod.GET, header, Map.class)
                .get("total_count");
    }

    /**
     * 사용자 Repo 리스트
     * 어디에 사용할지 까먹음
     */
    private GithubRepoDto getOAuthMemberRepository(String owner, HttpHeaders header) {
        UriComponents uri = UriComponentsBuilder
                .fromHttpUrl("https://api.github.com/search/repositories?q=user:" + owner + " fork:true").build();
        return githubRestService.rest(uri.toString(), HttpMethod.GET, header, GithubRepoDto.class);
    }

    /**
     * Repository에서 가장 많이 사용된 상위 3개 언어 추출
     */
    private String getMostLanguage(List<RepositoryItem> repositoryItems) {
        Map<String, Integer> languageMap = new HashMap<>();
        //데이터중에서 프로그래밍 언어만 추출
        repositoryItems.stream().filter(repositoryItem -> repositoryItem.getLanguage() != null)
                .map(RepositoryItem::getLanguage)
                .forEach(s -> {
                    if (languageMap.containsKey(s)) {
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
        for (Map.Entry<String, Integer> entry : entries) {
            languageList.add(entry.getKey());
            if (++cnt == 3) {
                break;
            }
        }
        return String.join(",", languageList);
    }
}
