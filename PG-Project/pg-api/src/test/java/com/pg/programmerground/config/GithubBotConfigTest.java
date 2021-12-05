package com.pg.programmerground.config;

import com.pg.pgp.config.GithubBotConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GithubBotConfigTest {
  @Autowired
  private GithubBotConfig githubBotConfig;

  @Test
  void yamlFileTest() {
    String token = githubBotConfig.getToken();
    String orgUrl = githubBotConfig.getOrgUrl();
    String orgRepoUrl = githubBotConfig.getOrgRepoUrl();

    System.out.println("Bot Token :  " + token);
    System.out.println("Github Org URL : " + orgUrl);
    System.out.println("Github Org Repo URL " + orgRepoUrl); 

    assertAll(
        () -> assertTrue(token.contains("bearer ")),
        () -> assertEquals(orgUrl, "https://api.github.com/orgs/programmer-ground"),
        () -> assertEquals(orgRepoUrl, "https://api.github.com/repos/programmer-ground")
    );
  }
}