package com.pg.pgp.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "github")
public class GithubBotConfig {
  private String token;
  private String orgUrl;
  private String orgRepoUrl;
}
