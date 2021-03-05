package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.common.BooleanToYNConverter;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "PLAYGROUND_APPLY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaygroundApply extends BaseTimeEntity {
    private static final boolean LEADER_APPLY_YN = true;

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
    private boolean applyYn = false;

    @Builder
    public PlaygroundApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition, boolean applyYn) {
        this.user = user;
        this.playground = playground;
        this.playgroundPosition = playgroundPosition;
        this.applyYn = applyYn;
    }

    /**
     * Playground 생성시 리더의 포지션을 넣기 위한 함수
     */
    public static PlaygroundApply createLeaderApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition) {
        PlaygroundApply playgroundApply = PlaygroundApply.builder()
                .user(user)
                .playground(playground)
                .playgroundPosition(playgroundPosition)
                .applyYn(LEADER_APPLY_YN)       //리더는 포지션에 넣음
                .build();
        user.getApplyPlaygrounds().add(playgroundApply);
        playground.getApplyPlaygrounds().add(playgroundApply);
        playgroundPosition.setPlaygroundApply(playgroundApply);
        return playgroundApply;
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
