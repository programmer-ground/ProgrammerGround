package com.pg.programmerground.playground;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.pg.programmerground.TestUserManagement;
import com.pg.programmerground.model.Oauth2AuthorizedClientRepository;
import com.pg.programmerground.service.GithubRestService;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeAll;
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
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PlayGroundBotTest {

  private static final Long GITHUB_BOT_ID = 83174448L;
  private static final String PREFIX_GITHUB_API = "https://api.github.com/orgs/programmer-ground";
  private static final String PREFIX_ACCESS_TOKEN = "Bearer ";
  private static final RestTemplate restTemplate = new RestTemplate();
  TestJsonPackage dtoList;

  @Autowired
  private GithubRestService githubRestService;

  @Autowired
  TestUserManagement management;

  @Autowired
  private Oauth2AuthorizedClientRepository oauth2AuthorizedClientRepository;


  @BeforeAll
  void dataSetUp() throws IOException {
    dtoList = TestJsonPackage.of();
    management.saveGithubRepoBotUser();
  }

  @Test
  @DisplayName("생성된 BOT 계정으로 소속 Organization의 정보를 얻을 수 있다.")
  public void get_github_org_by_bot_user() {
    //given
    HttpHeaders headers = getHeaders();
    //when
    String rest = githubRestService
        .rest(PREFIX_GITHUB_API, HttpMethod.GET, headers, String.class);
    //then
    System.out.println(rest);
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
        .postForEntity(PREFIX_GITHUB_API + "/repos", request, String.class);
    //then
    assertEquals(responseEntity.getStatusCode(), HttpStatus.CREATED);
  }



  private HttpHeaders getHeaders() {
    String githubBotToken = oauth2AuthorizedClientRepository
        .findById(GITHUB_BOT_ID)
        .orElseThrow(IllegalArgumentException::new)
        .getAccessTokenValue();

    HttpHeaders headers = new HttpHeaders();
    headers.set("Accept", "application/vnd.github.v3+json");
    headers.set("Authorization", PREFIX_ACCESS_TOKEN + githubBotToken);
    return headers;
  }

}
