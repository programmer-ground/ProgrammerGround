package com.pg.programmerground.playground;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pg.programmerground.TestUserManagement;
import com.pg.programmerground.auth.jwt.JwtAuthenticationToken;
import com.pg.programmerground.config.GithubBotConfig;
import com.pg.programmerground.domain.Playground;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.model.PlaygroundRepository;
import com.pg.programmerground.service.GithubRestService;
import com.pg.programmerground.service.OAuthUserService;
import com.pg.programmerground.service.PlaygroundService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayGroundBotTest {

  private static final Long GITHUB_ID = 1234L;
  private static final RestTemplate restTemplate = new RestTemplate();
  TestJsonPackage dtoList;

  @Autowired
  private GithubBotConfig githubBotConfig;

  @Autowired
  private GithubRestService githubRestService;

  @Autowired
  TestUserManagement management;

  @Autowired
  private OAuthUserService oAuthUserService;

  @Autowired
  private PlaygroundService playgroundService;

  @Autowired
  private PlaygroundRepository playgroundRepository;


  @BeforeAll
  void dataSetUp() throws IOException {
    dtoList = TestJsonPackage.of();
    management.saveTestUser();
    management.saveGithubRepoBotUser();
  }

  @BeforeEach
  void authenticationSetUp() {
    //Github 로그인을 한번 시도하고
    UserDetails userDetails = oAuthUserService.loadUserByOAuthId(GITHUB_ID);
    Authentication authentication = new JwtAuthenticationToken(userDetails, "토큰 값 필요없음", userDetails.getAuthorities());
    SecurityContextHolder.getContext().setAuthentication(authentication);
  }

  @Test
  @DisplayName("생성된 BOT 계정으로 소속 Organization의 정보를 얻을 수 있다.")
  public void get_github_org_by_bot_user() {
    //given
    HttpHeaders headers = getHeaders();
    //when
    String rest = githubRestService
        .rest(githubBotConfig.getOrgUrl(), HttpMethod.GET, headers, String.class);
    //then
    System.out.println(rest);
  }

  @Test
  @DisplayName("PlayGround의 Leader가 Github Repository 생성 요청을 할 수 있다")
  public void playground_leader_request_create_github_repo() throws Exception {
    //given
    PlaygroundApi playgroundApi = dtoList.createPlayground;
    Long playgroundId = playgroundService.createPlayground(playgroundApi);
    Playground playground = playgroundRepository.findById(playgroundId).orElseThrow();

    //when
    playgroundService.getPlaygroundDetailInfo(playgroundId);

    //then
  }

  @Test
  @DisplayName("생성된 BOT 계정으로 Repo를 생성할 수 있다")
  public void create_github_repo_by_bot_user() {
    /*
     * {
     *   "name": "<Repo Name>",
     *   "description": "<Some message>",
     *   "homepage": "https://github.com",
     *   "private": false,
     * }
     */
    //given
    Map<String, String> requestBody = new HashMap<>();
    requestBody.put("name", "test-pg-bot-repo");
    HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, getHeaders());
    //when
    ResponseEntity<String> responseEntity = restTemplate
        .postForEntity(githubBotConfig.getOrgUrl() + "/repos", request, String.class);
    //then
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
  }

  @Test
  @DisplayName("해당 Repository에 유저를 Collaborator로 추가할 수 있다")
  public void add_collaborator_github_repo() {
    //given
    String targetUrl = githubBotConfig.getOrgRepoUrl() + "/test-repo/collaborators/pg-test-user-1";
    HttpEntity<?> entity = new HttpEntity<>(getHeaders());

    //when
    ResponseEntity<String> exchange = restTemplate
        .exchange(targetUrl, HttpMethod.PUT, entity, String.class);

    //then
    assertEquals(exchange.getStatusCode(), HttpStatus.CREATED);
  }

  @Test
  @DisplayName("해당 Repository에 추가할 유저가 Collabrator로 등록이 안되어있을 시 404 익셉션이 발생한다")
  public void if_not_exist_collaborator_github_repo() {
    //given
    String targetUrl = githubBotConfig.getOrgRepoUrl() +"/test-repo/collaborators/pg-test-user-1";
    HttpEntity<?> entity = new HttpEntity<>(getHeaders());

    //when
    HttpClientErrorException thrown = assertThrows(HttpClientErrorException.class,
        () -> restTemplate
            .exchange(targetUrl, HttpMethod.GET, entity, String.class)
    );

    //then
    assertEquals(thrown.getStatusCode(), HttpStatus.NOT_FOUND);
  }

  @Test
  @DisplayName("해당 Repository에 추가할 유저가 Collabrator로 등록이 되어있을 시 204 No Content 리스폰스가 발생한다")
  public void if_exist_collaborator_github_repo() {
    //given
    String targetUrl = githubBotConfig.getOrgRepoUrl() + "/test-repo/collaborators/pg-test-user-2";
    HttpEntity<?> entity = new HttpEntity<>(getHeaders());

    //when
    ResponseEntity<String> exchange = restTemplate
        .exchange(targetUrl, HttpMethod.GET, entity, String.class);

    //then
    assertEquals(exchange.getStatusCode(), HttpStatus.NO_CONTENT);
  }

  private HttpHeaders getHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/vnd.github.v3+json");
    headers.set("Authorization", githubBotConfig.getToken());
    return headers;
  }

}
