package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Entity(name = "PLAYGROUND")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playground extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYGROUND_ID")
    private Long id;

    @Column(name = "MAX_MEMBER_COUNT")
    private int maxMemberCount;

    @Column(name = "CURRENT_MEMBER_COUNT")
    private int currentMemberCount = 1;     //Playground를 생성하면 Leader가 포함되기 때문에 기본값 1

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PlaygroundApply> applyPlaygrounds = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEADER_USER_ID")
    private OAuthUser leader;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaygroundPosition> playgroundPositionList = new ArrayList<>();

    @Builder
    private Playground(int maxMemberCount, String title, String description) {
        this.maxMemberCount = maxMemberCount;
        this.title = title;
        this.description = description;
    }

    /**
     * playground 객체 생성
     * playground 정보 builer로 생성
     * playground 객체에 oAuthUser가 등록된 연관 객체(oAuthUserPlayground) 등록
     */
    public static Playground createPlayground(PlaygroundApi playgroundInfo, OAuthUser leader, List<PlaygroundPosition> playgroundPositionList) {
        Playground playground = Playground.builder()
                .title(playgroundInfo.getTitle())
                .description(playgroundInfo.getDescription())
                .maxMemberCount(playgroundInfo.getMaxUserNum())
                .build();
        playgroundPositionList.forEach(playground::addPosition);
        playground.addLeader(leader);
        return playground;
    }

    /**
     * Playground의 Position 등록
     */
    private void addPosition(PlaygroundPosition position) {
        this.playgroundPositionList.add(position);
        position.setPlayground(this);
    }

    /**
     * Playground의 Leader 설정
     */
    private void addLeader(OAuthUser leader) {
        this.leader = leader;
        leader.getLeaderPlaygrounds().add(this);
    }

    /**
     * Position ID로 탐색
     */
    public PlaygroundPosition searchPosition(Long positionId) {
        return this.playgroundPositionList.stream()
                .filter(playgroundPosition -> playgroundPosition.getId().equals(positionId))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("포지션이 존재하지 않음"));
    }

    /**
     * 이미 해당 Playground에 참여중인 유저인지 체크
     */
    public boolean checkAlreadyMember(OAuthUser user) {
        return applyPlaygrounds.stream().noneMatch(playgroundApply -> {
            return playgroundApply.getUser() == user;
        });
    }
}
