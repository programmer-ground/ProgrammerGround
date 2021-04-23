package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.enumerated.ApplyStatus;
import com.pg.programmerground.exception.WrongRequestException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

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

    @ManyToOne
    @JoinColumn(name = "PLAYGROUND_POSITION_ID")
    private PlaygroundPosition playgroundPosition;

    @Column(name = "APPLY_STATUS")
    @Enumerated(EnumType.STRING)
    private ApplyStatus applyYn;

    @Builder
    public PlaygroundApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition, ApplyStatus applyYn) {
        Assert.notNull(user, "user must not be null");
        Assert.notNull(playground, "playground must not be null");
        Assert.notNull(playgroundPosition, "playgroundPosition must not be null");
        Assert.notNull(applyYn, "applyYn must not be null");
        this.user = user;
        this.playground = playground;
        this.playgroundPosition = playgroundPosition;
        this.applyYn = applyYn;
    }

    /**
     * 생각해봐야 할 것 : 여러 요청이 온 상황에 하나의 요청을 수락해서 인원이 가득찰 경우 나머지 요청 처리 여부
     */
    public void acceptApply(OAuthUser user) {
        this.playground.isLeaderUser(user);
        this.applyYn = ApplyStatus.ACCEPT;
        this.playgroundPosition.increaseMemberNum();
        this.playground.increaseMemberNum();
    }

    public void rejectApply(OAuthUser user) {
        this.playground.isLeaderUser(user);
        this.applyYn = ApplyStatus.REJECT;
    }
    /**
     * Playground 생성시 리더의 포지션을 넣기 위한 함수
     */
    public static void createLeaderApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition) throws Exception {
        PlaygroundApply playgroundApply = PlaygroundApply.builder()
                .user(user)
                .playground(playground)
                .playgroundPosition(playgroundPosition)
                .applyYn(ApplyStatus.ACCEPT)       //리더는 포지션에 넣음
                .build();
        playgroundPosition.increaseMember();        //Position 증가
        user.getApplyPlaygrounds().add(playgroundApply);
        playground.getApplyPlaygrounds().add(playgroundApply);
        playgroundPosition.addPlaygroundApply(playgroundApply);
    }

    /**
     * User가 Playground 신청
     */
    public static void createUserApply(OAuthUser user, Playground playground, PlaygroundPosition playgroundPosition) {
        /**
         * 포지션의 현재 인원이 꽉차있다면 예외처리
         * 이미 신청했거나, 수락됐거나, 거절당했을 시
         * 버그 유발 방지
         * 프론트단에서 확인(인원 검사, 이미 참여 여부) 요청을 통해 처리해야함
         */
        if (playgroundPosition.isFullPosition()) {
            throw new WrongRequestException("해당 포지션의 인원 가득참");
        }
        if (playground.checkAlreadyMember(user)) {
            throw new WrongRequestException("이미 참여 신청했거나 가입되어있거나 거절당함");
        }
        PlaygroundApply playgroundApply = PlaygroundApply.builder()
                .user(user)
                .playground(playground)
                .playgroundPosition(playgroundPosition)
                .applyYn(ApplyStatus.WAIT)
                .build();
        //양방향 관계 매핑
        user.getApplyPlaygrounds().add(playgroundApply);
        playground.getApplyPlaygrounds().add(playgroundApply);
        playgroundPosition.addPlaygroundApply(playgroundApply);
    }

    public boolean isAlreadyMember(OAuthUser user) {
        return this.user.equals(user) && this.applyYn != ApplyStatus.REJECT;
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
