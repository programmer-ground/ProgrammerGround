package com.pg.pgp.domain.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class GithubRepositoryInfo {

  @Id
  @Column(name = "USER_GITHUB_REPO_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  /*@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "MEMBER_GITHUB_INFO_ID")
  private MemberGithubInfo memberGithubInfo;
*/
  private Long repositoryId;

  private String name;

  @JsonProperty("full_name")
  private String fullName;

  @JsonProperty("html_url")
  private String htmlUrl;

  private String description;

  @JsonProperty("languages_url")
  private String languagesUrl;

  @JsonProperty("commits_url")
  private String commitsUrl;

  private String language;
}
