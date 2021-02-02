package com.pg.auth.domain.github;

import com.pg.auth.domain.OAuthMember;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class MemberGithubInfo {

  @Id
  @Column(name = "MEMBER_GITHUB_INFO_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "memberGithubInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private OAuthMember member;
  private int commitCnt;
  private int pullRequestCnt;
  private String mostLanguage;
  private String githubPage;
  private int repositoryCnt;
  private String profileImg;

  /*@OneToMany(mappedBy = "memberGithubInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private List<GithubRepositoryInfo> githubRepositories = new ArrayList<>();

  public void addGithubRepository(GithubRepositoryInfo githubRepositoryInfo) {
      githubRepositories.add(githubRepositoryInfo);
  }*/

  public void updateInfo(MemberGithubInfo memberGithubInfo) {
    this.commitCnt = memberGithubInfo.commitCnt;
    this.pullRequestCnt = memberGithubInfo.pullRequestCnt;
    this.mostLanguage = memberGithubInfo.mostLanguage;
    this.repositoryCnt = memberGithubInfo.repositoryCnt;
  }
}
