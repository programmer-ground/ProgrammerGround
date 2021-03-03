package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.dto.playground.MakePositionInfo;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "PLAYGROUND_POSITION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaygroundPosition extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "PLAYGROUND_POSITION_ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POSITION")
    private Position position;

    @Column(name = "MAX_POSITION_NUM")
    private int maxUserNum;

    @Column(name = "CURRENT_POSITION_NUM")
    private int currentUserNum;

    @OneToOne(mappedBy = "playgroundPosition", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlaygroundApply playgroundApply;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "PLAYGROUND_ID")
    private Playground playground;

    @Builder
    private PlaygroundPosition(String position, int maxUserNum) {
        this.position = Position.valueOf(position);
        this.maxUserNum = maxUserNum;
        this.currentUserNum = 0;
    }
    public static List<PlaygroundPosition> createPosition(List<MakePositionInfo> positionInfoList) {
        return positionInfoList.stream()
                .map(makePositionInfo -> {
                    return PlaygroundPosition.builder()
                            .position(makePositionInfo.getPositionName())
                            .maxUserNum(makePositionInfo.getPositionMaxNum())
                            .build();
                }).collect(Collectors.toList());
    }
}
