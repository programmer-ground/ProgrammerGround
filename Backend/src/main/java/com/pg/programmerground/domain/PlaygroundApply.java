package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.enumerated.ApplyStatus;
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

    @Column(name = "APPLY_STATUS")
    @Enumerated(EnumType.STRING)
    private ApplyStatus applyYn;

    @Builder
    public PlaygroundApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition, ApplyStatus applyYn) {
        this.user = user;
        this.playground = playground;
        this.playgroundPosition = playgroundPosition;
        this.applyYn = applyYn;
    }

    /**
     * Playground 생성시 리더의 포지션을 넣기 위한 함수
     */
    public static PlaygroundApply createLeaderApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition) throws Exception {
        PlaygroundApply playgroundApply = PlaygroundApply.builder()
                .user(user)
                .playground(playground)
                .playgroundPosition(playgroundPosition)
                .applyYn(ApplyStatus.ACCEPT)       //리더는 포지션에 넣음
                .build();
        playgroundPosition.increaseMember();        //Position 증가
        user.getApplyPlaygrounds().add(playgroundApply);
        playground.getApplyPlaygrounds().add(playgroundApply);
        playgroundPosition.setPlaygroundApply(playgroundApply);
        return playgroundApply;
    }

    /**
     * User Playground 신청
     * 추후 Playground 신청시 예외처리 추가
     */
    public static PlaygroundApply createUserApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition) throws Exception {
        /**
         * 포지션의 현재 인원이 꽉차있다면 예외처리
         * 이미 신청했거나, 수락됐거나, 거절당했을 시
         * 버그 유발 방지
         * 프론트단에서 확인(인원 검사, 이미 참여 여부) 요청을 통해 처리해야함
         */
        if (!playgroundPosition.checkFullPosition()) {
            throw new Exception("해당 포지션의 인원 가득참");
        }
        if (!playground.checkAlreadyMember(user)) {
            throw new Exception("이미 참여 신청했거나 가입되어있거나 거절당함");
        }
        PlaygroundApply playgroundApply = PlaygroundApply.builder()
                .user(user)
                .playground(playground)
                .playgroundPosition(playgroundPosition)
                .applyYn(ApplyStatus.WAIT)
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
