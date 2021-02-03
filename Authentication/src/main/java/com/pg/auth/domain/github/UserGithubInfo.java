package com.pg.auth.domain.github;

import com.pg.auth.domain.OAuthUser;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class UserGithubInfo {

  @Id
  @Column(name = "USER_GITHUB_INFO_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "userGithubInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private OAuthUser user;

  private int commitCnt;
  private int pullRequestCnt;
  private String mostLanguage;
  private int repositoryCnt;
  private String githubPage;
  private String profileImg;

  /*@OneToMany(mappedBy = "memberGithubInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<GithubRepositoryInfo> githubRepositories = new ArrayList<>();

  public void addGithubRepository(GithubRepositoryInfo githubRepositoryInfo) {
      githubRepositories.add(githubRepositoryInfo);
  }*/

  public void updateInfo(UserGithubInfo userGithubInfo) {
    this.commitCnt = userGithubInfo.commitCnt;
    this.pullRequestCnt = userGithubInfo.pullRequestCnt;
    this.mostLanguage = userGithubInfo.mostLanguage;
    this.repositoryCnt = userGithubInfo.repositoryCnt;
  }
}