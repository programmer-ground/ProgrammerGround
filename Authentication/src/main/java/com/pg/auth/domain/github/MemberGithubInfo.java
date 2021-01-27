package com.pg.auth.domain.github;

import com.pg.auth.domain.OAuthMember;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberGithubInfo {

  @Id
  @Column(name = "MEMBER_GITHUB_INFO_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "memberGithubInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private OAuthMember member;

  private int commitCnt;

  private int pullRequestCnt;

  private int startCnt;

  @OneToMany(mappedBy = "memberGithubInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private final List<GithubRepositoryInfo> githubRepositories = new ArrayList<>();

  public void addGithubRepository(GithubRepositoryInfo githubRepositoryInfo) {
      githubRepositories.add(githubRepositoryInfo);
  }
}
