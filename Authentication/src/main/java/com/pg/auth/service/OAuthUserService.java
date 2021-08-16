package com.pg.auth.service;

import com.pg.auth.domain.OAuthUser;
import com.pg.auth.domain.github.UserGithubInfo;
import com.pg.auth.domain.github.Oauth2AuthorizedClient;
import com.pg.auth.dto.GithubOAuthInfoDto;
import com.pg.auth.dto.GithubRepoDto;
import com.pg.auth.dto.JwtToken;
import com.pg.auth.dto.RepositoryItem;
import com.pg.auth.exception.InvalidCodeException;
import com.pg.auth.exception.OAuthLoginException;
import com.pg.auth.jwtConfig.JwtTokenProvider;
import com.pg.auth.repository.Oauth2AuthorizedClientRepository;
import com.pg.auth.repository.OAuthUserRepository;
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
public class OAuthUserService {
    private static final int VALID_CODE = 0;
    private final OAuthUserRepository oAuthUserRepository;
    private final Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RestService restService;

    //jdbc service에서 load하여 가져온다.
    public OAuthUserService(OAuthUserRepository oAuthUserRepository,
                            Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository, JwtTokenProvider jwtTokenProvider,
                            RestService restService) {
        this.oAuthUserRepository = oAuthUserRepository;
        this.oauth2AuthorizedClientRepository = oauth2AuthorizedClientRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.restService = restService;
    }

    /**
     * OAuth 인증 성공후 유저 생성
     * 이미 존재하는 유저의 경우 github정보를 업데이트하고 Code를 칼럼에 넣는다 (유저 생성도 똑같음)
     */
    @Transactional
    public OAuthUser createUser(OAuth2AuthenticationToken authentication) throws OAuthLoginException, ExecutionException, InterruptedException {
        Oauth2AuthorizedClient authorizedClient = oauth2AuthorizedClientRepository
            .findById(Long.valueOf(authentication.getName()))
            .orElseThrow(() -> new OAuthLoginException("OAuth 로그인 에러"));
        OAuthUser oAuthUser = oAuthUserRepository.findByOauth2AuthorizedClient(authorizedClient);
        //로그인 코드 생성
        String loginCode = makeUUID();

        //신규 유저 체크
        if (oAuthUser == null) {
            OAuth2User oAuth2User = authentication.getPrincipal();
            oAuthUser = OAuthUser.builder()
                .userName(oAuth2User.getAttribute("name") != null ? oAuth2User.getAttribute("name") : "No Name")
                .oauth2AuthorizedClient(authorizedClient)
                .OAuthName(oAuth2User.getAttribute("login") != null ? oAuth2User.getAttribute("login") : "No Nickname ")
                .code(loginCode)
                .Role(oAuth2User.getAuthorities().stream().
                    map(GrantedAuthority::getAuthority).
                    collect(Collectors.joining(",")))
                .build();
            updateGithubInfo(oAuthUser);
            return oAuthUserRepository.save(oAuthUser);
        }
        updateGithubInfo(oAuthUser);
        updateLoginCode(oAuthUser, loginCode);
        return oAuthUser;
    }

    /**
     * OAuth 인증 후 발급된 Code를 확인해 Jwt 발급
     */
    @Transactional
    public JwtToken jwtLogin(String code, Long id) throws InvalidCodeException {
        OAuthUser oAuthUser = oAuthUserRepository.findByCodeAndOauth2AuthorizedClient(code, oauth2AuthorizedClientRepository.findById(id).orElseThrow());
        if (oAuthUser == null || !validateLoginCode(code, oAuthUser)) {
            throw new InvalidCodeException("Login Code 에러");
        }
        oAuthUser.setCode("");   //인증 완료후 code는 지워준다.
        return createJwtToken(oAuthUser);
    }

    /**
     * Test Access Token 발급
     */
    public String testAccessToken(Long id) throws InvalidCodeException {
        OAuthUser oAuthUser = oAuthUserRepository.findByOauth2AuthorizedClient(oauth2AuthorizedClientRepository.findById(id).orElseThrow());
        if(oAuthUser == null) {
            throw new InvalidCodeException("유저 없음");
        }
        return createTestToken(oAuthUser);
    }

    /**
     *
     */
    public String createTestToken(OAuthUser oAuthUser) {
        return jwtTokenProvider.makeTestAccessToken(
                oAuthUser.getOauth2AuthorizedClient().getAccessTokenValue(),
                oAuthUser.getOauth2AuthorizedClient().getId(),
                oAuthUser.getId(),
                Arrays.stream(oAuthUser.getRole().split(",")).map(String::new).collect(Collectors.toList()));
    }

    /**
     * 로그인시 AccessToken, RefreshToken 토큰 생성
     */
    private JwtToken createJwtToken(OAuthUser oAuthUser) {
        return jwtTokenProvider.createTokens(
            oAuthUser.getOauth2AuthorizedClient().getId(),
            oAuthUser.getId(),
            Arrays.stream(oAuthUser.getRole().split(",")).map(String::new).collect(Collectors.toList()));
    }

    /**
     * refreshToken을 통한 재발급
     */
    public String reissuedAccessToken(String bearerToken) {
        OAuthUser oAuthUser = oauth2AuthorizedClientRepository.findById(jwtTokenProvider.getOAuthIdByRefreshToken(bearerToken)).orElseThrow().getUser();
        return jwtTokenProvider.createAccessToken(
                oAuthUser.getOauth2AuthorizedClient().getId(),
                oAuthUser.getId(),
                Arrays.stream(oAuthUser.getRole().split(",")).map(String::new).collect(Collectors.toList()));
    }

    /**
     * 코드 검증
     */
    private boolean validateLoginCode(String code, OAuthUser oAuthUser) {
        //유저 id와 code를 비교하여 판별
        return UUID.fromString(code).compareTo(UUID.fromString(oAuthUser.getCode())) == VALID_CODE;
    }

    /**
     * Github Total Data
     * repo
     * commit
     * star
     */
    private UserGithubInfo getGithubInfo(OAuthUser oAuthUser) {
        //Spring Seucrity UserDetails 객체 가져오기

        Oauth2AuthorizedClient oauthUser = oAuthUser.getOauth2AuthorizedClient();

        HttpHeaders header = new HttpHeaders();
        header.set("Authorization", "bearer " + oauthUser.getAccessTokenValue());
        GithubOAuthInfoDto oauthInfo = restService.rest(
            "https://api.github.com/user", HttpMethod.GET, header, GithubOAuthInfoDto.class);

        CompletableFuture<GithubRepoDto> repoFuture = CompletableFuture.supplyAsync(
            () -> getOAuthUserRepository(oauthInfo.getLogin(), header));
        CompletableFuture<Integer> pullRequestFuture = CompletableFuture.supplyAsync(
            () -> getOAuthPullRequestCount(oauthInfo.getLogin(), header));
        CompletableFuture<Integer> commitFuture = CompletableFuture.supplyAsync(
            () -> getOAuthCommitCount(oauthInfo.getLogin(), header));
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(repoFuture, pullRequestFuture,
            commitFuture);
        try {
            voidCompletableFuture.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int repoCnt = 0;
        List<RepositoryItem> repositoryItems = new ArrayList<>();
        try{
            repoCnt = repoFuture.join().getTotalCount();
            repositoryItems = repoFuture.join().getRepositoryItems();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return UserGithubInfo.builder()
            //프로필 이미지
            .profileImg(oauthInfo.getAvatarUrl())
            //github 유저 페이지
            .githubPage(oauthInfo.getHtmlUrl())
            //Commit 개수
            .commitCnt(commitFuture.join())
            //Repo 개수
            .repositoryCnt(repoCnt)
            //Issue, PR 수
            .pullRequestCnt(pullRequestFuture.join())
            //가장 많이 사용한 언어 수
            .mostLanguage(getMostLanguage(repositoryItems))
            .user(oAuthUser)
            .build();
    }

    /**
     * Github Commit 개수 가져오기
     */
    private Integer getOAuthCommitCount(String owner, HttpHeaders header) {
        header.set("Accept", "application/vnd.github.cloak-preview");
        return (Integer) restService.rest(
            "https://api.github.com/search/commits?q=author:" + owner, HttpMethod.GET, header, Map.class)
            .get("total_count");
    }

    /**
     * UUID 생성
     * 이렇게 함수로 빼야 테스트코드 작성이 쉬워지는 것 같음
     */
    private String makeUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Github PR, Issue 개수 가져오기
     */
    private Integer getOAuthPullRequestCount(String owner, HttpHeaders header) {
        header.set("Accept", "application/vnd.github.v3+json");
        return (Integer) restService.rest(
            "https://api.github.com/search/issues?q=" + owner, HttpMethod.GET, header, Map.class)
            .get("total_count");
    }

    /**
     * 사용자 Repo 리스트
     * 어디에 사용할지 까먹음
     */
    private GithubRepoDto getOAuthUserRepository(String owner, HttpHeaders header) {
        UriComponents uri = UriComponentsBuilder
            .fromHttpUrl("https://api.github.com/search/repositories?q=user:" + owner + " fork:true").build();
        return restService.rest(uri.toString(), HttpMethod.GET, header, GithubRepoDto.class);
    }

    /**
     * Repository에서 가장 많이 사용된 상위 3개 언어 추출
     */
    private String getMostLanguage(List<RepositoryItem> repositoryItems) {
        if(repositoryItems.isEmpty()) {
            return "No Repo";
        }
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

    /**
     * Github Info 업데이트
     */
    private void updateGithubInfo(OAuthUser oAuthUser) throws ExecutionException, InterruptedException {
        oAuthUser.updateUserGithubInfo(getGithubInfo(oAuthUser));
    }

    /**
     * LoginCode 업데이트
     */
    private void updateLoginCode(OAuthUser oAuthUser, String loginCode) {
        oAuthUser.updateLoginCode(loginCode);
    }
}
