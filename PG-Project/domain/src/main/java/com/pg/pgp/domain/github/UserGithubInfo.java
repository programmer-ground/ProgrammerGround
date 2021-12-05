package com.pg.pgp.domain.github;

import com.pg.pgp.domain.OAuthUser;
import lombok.*;

import javax.persistence.*;

@Entity(name = "USER_GITHUB_INFO")
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

  @Column(name ="COMMIT_CNT")
  private int commitCnt;
  @Column(name = "PULL_REQUEST_CNT")
  private int pullRequestCnt;
  @Column(name = "MOST_LANGUAGE")
  private String mostLanguage;
  @Column(name = "REPOSITORY_CNT")
  private int repositoryCnt;
  @Column(name = "GITHUB_PAGE")
  private String githubPage;
  @Column(name ="PROFILE_IMG")
  private String profileImg;

  public void updateInfo(UserGithubInfo userGithubInfo) {
    this.commitCnt = userGithubInfo.commitCnt;
    this.pullRequestCnt = userGithubInfo.pullRequestCnt;
    this.mostLanguage = userGithubInfo.mostLanguage;
    this.repositoryCnt = userGithubInfo.repositoryCnt;
  }
}
