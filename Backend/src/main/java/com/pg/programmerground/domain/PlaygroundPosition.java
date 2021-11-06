package com.pg.programmerground.domain;

import com.pg.programmerground.domain.common.BaseTimeEntity;
import com.pg.programmerground.domain.enumerated.Position;
import com.pg.programmerground.domain.enumerated.PositionLevel;
import com.pg.programmerground.dto.playground.api_req.PlaygroundApi;
import com.pg.programmerground.exception.FullMemberException;
import com.pg.programmerground.exception.WrongRequestException;
import lombok.*;
import org.springframework.util.Assert;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@Table(name = "PLAYGROUND_POSITION")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaygroundPosition extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYGROUND_POSITION_ID")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POSITION")
    private Position position;

    @Column(name = "MAX_POSITION_NUM")
    private int maxPositionNum;

    @Column(name = "CURRENT_POSITION_NUM")
    private int currentPositionNum;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "POSITION_LEVEL")
    private PositionLevel positionLevel;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PLAYGROUND_ID")
    private Playground playground;

    @OneToMany(mappedBy = "playgroundPosition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaygroundApply> playgroundApply = new ArrayList<>();

    @OneToMany(mappedBy = "playgroundPosition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PositionLanguage> positionLanguageList = new ArrayList<>();

    @Builder
    private PlaygroundPosition(String position, Integer maxPositionNum, List<PositionLanguage> positionLanguageList, PositionLevel positionLevel) {
        Assert.notNull(position, "position must not be null");
        Assert.notNull(maxPositionNum, "position must not be null");
        Assert.notNull(positionLanguageList, "position must not be null");
        Assert.notNull(positionLevel, "position must not be null");
        this.position = Position.valueOf(position);
        this.positionLanguageList = positionLanguageList;
        this.maxPositionNum = maxPositionNum;
        this.positionLevel = positionLevel;
        this.currentPositionNum = 0;
    }

    public static List<PlaygroundPosition> createPosition(PlaygroundApi playgroundApiList) {
        List<PlaygroundPosition> list =
                playgroundApiList.getPositionInfo().stream()
                        .map(positionApi -> {
                            PlaygroundPosition playgroundPosition =
                                    PlaygroundPosition.builder()
                                            .position(positionApi.getPositionName())
                                            .maxPositionNum(positionApi.getPositionMaxNum())
                                            .positionLevel(PositionLevel.valueOf(positionApi.getPositionLevel()))
                                            .positionLanguageList(PositionLanguage.createPositionLanguage(positionApi.getPositionLanguage()))
                                            .build();
                            //양방향 설정
                            for (PositionLanguage positionLanguage : playgroundPosition.positionLanguageList) {
                                positionLanguage.setPlaygroundPosition(playgroundPosition);
                            }
                            return playgroundPosition;
                        }).collect(Collectors.toList());
        if(!isCorrectMemberNum(playgroundApiList.getMaxUserNum(), list)) {
            throw new WrongRequestException("각 포지션 인원수와 최대 인원수가 다름");
        }
        return list;
    }

    /**
     * Playground에 저장된 MaxNum과 각 Position정보의 MaxNum의 합이 같은지 체크
     */
    private static boolean isCorrectMemberNum(int playgroundMaxNum, List<PlaygroundPosition> playgroundPositionList) {
        int positionTotalNum = playgroundPositionList.stream().mapToInt(PlaygroundPosition::getMaxPositionNum).sum();
        return positionTotalNum == playgroundMaxNum;
    }

    /**
     * Leader Position을 생성시 입력받은 Position중에 탐색
     */
    public static PlaygroundPosition searchLeaderPosition(List<PlaygroundPosition> playgroundPositions, String positionName) {
        //입력받은 Position중에 Leader가 신청한 Position탐색
        return playgroundPositions.stream()
                .filter(playgroundPosition -> playgroundPosition.getPosition().name().equals(positionName))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("입력되지 않은 포지션입니다"));
    }

    public void increaseMember() throws Exception {
        if (maxPositionNum <= currentPositionNum) {
            throw new Exception("Position 최대 인원을 넘음");
        }
        currentPositionNum++;
    }

    public boolean isFullPosition() {
        return maxPositionNum <= currentPositionNum;
    }

    public void increaseMemberNum() {
        if (isFullPosition()) {
            throw new FullMemberException("해당 포지션 멤버가 가득참");
        }
        currentPositionNum++;
    }

    public void addPlaygroundApply(PlaygroundApply playgroundApply) {
        this.playgroundApply.add(playgroundApply);
    }
}
