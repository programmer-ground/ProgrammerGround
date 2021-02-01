package com.pg.programmerground.domain;

import io.jsonwebtoken.lang.Assert;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthMember extends BaseTimeEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "MEMBER_ID")
  private Long id;

  //사용자 이름
  @Column(name = "MEMBER_NAME")
  private String userName;

  //Github 닉네임
  @Column(name = "OAUTH_NAME")
  private String OAuthName;

  @Column(name = "ROLE")
  private String Role;

  //JWT 로그인 인증 코드
  @Column(name = "CODE")
  private String code;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "OAUTH_ID")
  private Oauth2AuthorizedClient oauth2AuthorizedClient;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "MEMBER_GITHUB_INFO_ID")
  private MemberGithubInfo memberGithubInfo;

  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "MEMBER_EXTRA_INFO_ID")
  private MemberExtraInfo memberExtraInfo;

  @Builder
  public OAuthMember(String userName, String OAuthName, String Role, String code, Oauth2AuthorizedClient oauth2AuthorizedClient) {
    Assert.notNull(userName, "userName must not be null");
    Assert.notNull(OAuthName, "OAuthName must not be null");
    Assert.notNull(Role, "Role must not be null");
    Assert.notNull(code, "code must not be null");
    Assert.notNull(oauth2AuthorizedClient, "AuthorizedClient must not be null");

    this.userName = userName;
    this.OAuthName = OAuthName;
    this.Role = Role;
    this.code = code;
    this.oauth2AuthorizedClient = oauth2AuthorizedClient;
  }

  /*public void updateMemberGithubInfo(MemberGithubInfo memberGithubInfo) {
    Assert.notNull(memberGithubInfo, "memberGithubInfo must not be null");
    this.memberGithubInfo = memberGithubInfo;
  }

  public void updateLoginCode(UUID loginCode) {
    this.code = loginCode.toString();
  }

  public void updateLoginCode(String loginCode) {
    this.code = loginCode;
  }*/

}
