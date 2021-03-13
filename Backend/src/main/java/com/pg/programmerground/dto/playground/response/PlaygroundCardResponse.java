package com.pg.programmerground.dto.playground.response;

import com.pg.programmerground.domain.Playground;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 메인 페이지에 노출될 playground 리스트에 각각 표현될 정보들
 * 제목
 * 설명
 * 리더
 * 추후 추가...
 */
@Getter
@NoArgsConstructor
public class PlaygroundCardResponse {
    private Long id;
    private String title;
    private int maxMemberNum;
    private int currentMemberNum;
    private String leaderUserName;
    private List<PlaygroundCardPositionResponse> positionList;

    @Builder
    public PlaygroundCardResponse(Long id, String title, int maxMemberNum, int currentMemberNum, String leaderUserName, List<PlaygroundCardPositionResponse> positionList) {
        this.id = id;
        this.title = title;
        this.maxMemberNum = maxMemberNum;
        this.currentMemberNum = currentMemberNum;
        this.leaderUserName = leaderUserName;
        this.positionList = positionList;
    }

    /**
     * playground 목록에 출력될 데이터
     */
    public static List<PlaygroundCardResponse> createPlaygroundCardList(List<Playground> playgrounds) {
        return playgrounds.stream().map(playground -> PlaygroundCardResponse.builder()
                .id(playground.getId())
                .title(playground.getTitle())
                .maxMemberNum(playground.getMaxMemberCount())
                .currentMemberNum(playground.getCurrentMemberCount())
                .positionList(PlaygroundCardPositionResponse.createPositionList(playground.getPlaygroundPositionList()))
                .leaderUserName(playground.getLeader().getUserName()).build()).collect(Collectors.toList());
    }
}
