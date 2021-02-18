package com.pg.programmerground.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "TBL_USER_PLAYGROUND")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthUserPlayground extends BaseTimeEntity {

  @Id
  @Column(name = "USER_PLAYGROUND_ID")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "OAUTH_USER_ID")
  private OAuthUser user;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "PLAYGROUND_ID")
  private Playground playground;

  public static OAuthUserPlayground createOAuthUserPlayground(OAuthUser user) {
    OAuthUserPlayground userPlayground = new OAuthUserPlayground();
    userPlayground.user = user;
    return userPlayground;
  }

  public void updateLeader(OAuthUserPlayground userPlayground) {
    if(userPlayground.getPlayground().getCurrentMemberCount() <= 1) {
        playground.updateLeader(userPlayground.getUser());
    }
  }

  public void addPlayground(Playground playground) {
    this.playground = playground;
    updateLeader(this);
    playground.increaseCurrentMemberCount();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof OAuthUserPlayground)) {
      return false;
    }
    OAuthUserPlayground that = (OAuthUserPlayground) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
