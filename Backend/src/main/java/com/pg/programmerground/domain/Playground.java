package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.dto.playground.MakePlaygroundInfoDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "PLAYGROUND")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Playground extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYGROUND_ID")
    private Long id;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<PlaygroundApply> userPlaygrounds = new ArrayList<>();

    @Column(name = "MAX_MEMBER_COUNT")
    private int maxMemberCount;

    @Column(name = "CURRENT_MEMBER_COUNT")
    private int currentMemberCount = 0;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEADER_USER_ID")
    private OAuthUser leader;

    @Builder
    public Playground(int maxMemberCount, String title, String description) {
        this.maxMemberCount = maxMemberCount;
        this.title = title;
        this.description = description;
    }

    /**
     * playground 객체 생성
     * playground 정보 builer로 생성
     * playground 객체에 oAuthUser가 등록된 연관 객체(oAuthUserPlayground) 등록
     */
    public static Playground createPlayground(PlaygroundApply userPlayground, MakePlaygroundInfoDto playgroundInfo) {
        Playground playground = Playground.builder()
                .title(playgroundInfo.getTitle())
                .description(playgroundInfo.getDescription())
                .maxMemberCount(playgroundInfo.getMaxUserNum())
                .build();

        playground.addUserPlayground(userPlayground);

        return playground;
    }

    /**
     * Playground 객체에 UserPlayground 객체 등록
     * UserPlayground 객체에도 Playground 객체 등록 -> 양방향 관계를 위해
     */
    public void addUserPlayground(PlaygroundApply userPlayground) {
        userPlaygrounds.add(userPlayground);
        userPlayground.addPlayground(this);
    }

    public void updateLeader(OAuthUser user) {
        leader = user;
        user.addLeaderPlayground(this);
    }

    public void increaseCurrentMemberCount() {
        int current = this.maxMemberCount - currentMemberCount;

        if(current < 0 || current > this.maxMemberCount) {
            //TODO : Exception 처리해야함
        }
        this.currentMemberCount++;
    }
}
