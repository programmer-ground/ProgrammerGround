package com.pg.programmerground.dto.playground.response;


import com.pg.programmerground.domain.PlaygroundPosition;
import com.pg.programmerground.domain.PositionLanguage;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor
public class PlaygroundCardPositionResponse {
    private String positionName;
    private int maxPositionNum;
    private int currentPositionNum;
    private List<String> language;

    @Builder
    public PlaygroundCardPositionResponse(String positionName, int maxPositionNum, int currentPositionNum, List<String> language) {
        this.positionName = positionName;
        this.maxPositionNum = maxPositionNum;
        this.currentPositionNum = currentPositionNum;
        this.language = language;
    }

    public static List<PlaygroundCardPositionResponse> createPositionList(List<PlaygroundPosition> playgroundPositionList) {
        return playgroundPositionList.stream()
                .map(playgroundPosition -> {
                    return PlaygroundCardPositionResponse.builder()
                            .positionName(playgroundPosition.getPosition().name())
                            .maxPositionNum(playgroundPosition.getMaxPositionNum())
                            .currentPositionNum(playgroundPosition.getCurrentPositionNum())
                            .language(playgroundPosition.getPositionLanguageList().stream().map(PositionLanguage::getLanguageName).collect(Collectors.toList()))
                            .build();
                }).collect(Collectors.toList());
        //positionlist 던져주는 것까지 완료
    }
}
