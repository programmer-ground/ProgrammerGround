package com.pg.auth.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberExtraInfo {

  @Id
  @Column(name = "MEMBER_EXTRA_INFO_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "memberExtraInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private OAuthMember member;

}
