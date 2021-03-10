package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.enumerated.Position;
import com.pg.programmerground.dto.playground.MakePositionInfoDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "PLAYGROUND_POSITION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class
PlaygroundPosition extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "PLAYGROUND_POSITION_ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POSITION")
    private Position position;

    @Column(name = "MAX_POSITION_NUM")
    private int maxPositionNum;

    @Column(name = "CURRENT_POSITION_NUM")
    private int currentPositionNum;

    @OneToOne(mappedBy = "playgroundPosition", cascade = CascadeType.ALL, orphanRemoval = true)
    private PlaygroundApply playgroundApply;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYGROUND_ID")
    private Playground playground;

    @OneToMany(mappedBy = "playgroundPosition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PositionLanguage> positionLanguageList = new ArrayList<>();

    @Builder
    private PlaygroundPosition(String position, int maxPositionNum, List<PositionLanguage> positionLanguageList) {
        this.position = Position.valueOf(position);
        this.positionLanguageList = positionLanguageList;
        this.maxPositionNum = maxPositionNum;
        this.currentPositionNum = 0;
    }

    public static List<PlaygroundPosition> createPosition(List<MakePositionInfoDto> positionInfoList) {
        return positionInfoList.stream()
                .map(makePositionInfoDto -> {
                    PlaygroundPosition playgroundPosition =
                            PlaygroundPosition.builder()
                                    .position(makePositionInfoDto.getPositionName())
                                    .maxPositionNum(makePositionInfoDto.getPositionMaxNum())
                                    .positionLanguageList(PositionLanguage.createPositionLanguage(makePositionInfoDto.getPositionLanguage()))
                                    .build();
                    for (PositionLanguage positionLanguage : playgroundPosition.positionLanguageList) {
                        positionLanguage.setPlaygroundPosition(playgroundPosition);
                    }
                    return playgroundPosition;
                }).collect(Collectors.toList());
    }

    public void increaseMember() throws Exception {
        if (maxPositionNum <= currentPositionNum) {
            throw new Exception("Position 최대 인원을 넘음");
        }
        currentPositionNum++;
    }

    public boolean checkFullPosition() {
        return currentPositionNum < maxPositionNum;
    }
}
