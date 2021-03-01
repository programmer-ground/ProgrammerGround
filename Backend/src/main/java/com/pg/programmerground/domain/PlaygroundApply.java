package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.common.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "PLAYGROUND_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaygroundApply extends BaseTimeEntity {

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

  @OneToOne
  @JoinColumn(name = "PLAYGROUND_POSITION_ID")
  private PlaygroundPosition playgroundPosition;

  @Column(name = "APPLY_YN")
  @Convert(converter = BooleanToYNConverter.class)
  private boolean applyYn;

  /**
   * oAuthUser와 playground 연관 객체 생성
   * 연관 객체에 oAuthUser 등록
   * oAuthUser 객체에도 연관 객체 등록 -> 양방향 관계
   */
  public static PlaygroundApply createOAuthUserPlayground(OAuthUser user) {
    PlaygroundApply userPlayground = new PlaygroundApply();
    userPlayground.user = user;
    user.addUserPlayground(userPlayground);
    return userPlayground;
  }

  //리더를 설정하는 메소드는 playground에 있는것이 낫지 않을까
  public void updateLeader(PlaygroundApply userPlayground) {
    if(userPlayground.getPlayground().getCurrentMemberCount() <= 1) {
        playground.updateLeader(userPlayground.getUser());
    }
  }

  /**
   * OAuthUser 등록된 엔티티에 playground 등록
   */
  public void addPlayground(Playground playground) {
    playground.increaseCurrentMemberCount();
    this.playground = playground;
    updateLeader(this);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof PlaygroundApply)) {
      return false;
    }
    PlaygroundApply that = (PlaygroundApply) o;
    return Objects.equals(getId(), that.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId());
  }
}
