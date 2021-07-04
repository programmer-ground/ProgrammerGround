package com.pg.programmerground.util;

import org.springframework.http.HttpHeaders;

public class GithubHttpUtil {
  private final static String AUTH_PREFIX = "Authorization";
  private final static String ACCEPT_PREFIX = "Accept";
  private final static String ACCEPT_GITHUB_HEADER = "application/vnd.github.v3+json";

  public static HttpHeaders generateGithubApiHeader(String token) {
    HttpHeaders headers = new HttpHeaders();
    headers.set(ACCEPT_PREFIX, ACCEPT_GITHUB_HEADER);
    headers.set(AUTH_PREFIX, token);
    return headers;
  }

  public static String generateCollaboratorUrl(String repoTitle, String userName) {
    return "/" + repoTitle + "/collaborator/" + userName;
  }
}
