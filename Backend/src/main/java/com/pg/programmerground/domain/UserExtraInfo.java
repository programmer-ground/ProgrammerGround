package com.pg.programmerground.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "USER_EXTRA_INFO")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserExtraInfo {

  @Id
  @Column(name = "USER_EXTRA_INFO_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(mappedBy = "userExtraInfo", cascade = CascadeType.ALL, orphanRemoval = true)
  private OAuthUser user;

}
