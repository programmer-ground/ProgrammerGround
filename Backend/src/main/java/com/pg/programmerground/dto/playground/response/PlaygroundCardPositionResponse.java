package com.pg.programmerground.dto.playground.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.programmerground.domain.PlaygroundPosition;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlaygroundCardPositionResponse {
    @JsonProperty("position_name")
    private final String positionName;
    @JsonProperty("max_position_num")
    private final int maxPositionNum;
    @JsonProperty("current_position_num")
    private final int currentPositionNum;
    @JsonProperty("language")
    private final List<String> language;

    @Builder
    public PlaygroundCardPositionResponse(String positionName, int maxPositionNum, int currentPositionNum, List<String> language) {
        this.positionName = positionName;
        this.maxPositionNum = maxPositionNum;
        this.currentPositionNum = currentPositionNum;
        this.language = language;
    }

    public static List<PlaygroundCardPositionResponse> ofList(List<PlaygroundPosition> playgroundPositionList) {
        return playgroundPositionList.stream()
                .map(playgroundPosition -> {
                    return PlaygroundCardPositionResponse.builder()
                            .positionName(playgroundPosition.getPosition().name())
                            .maxPositionNum(playgroundPosition.getMaxPositionNum())
                            .currentPositionNum(playgroundPosition.getCurrentPositionNum())
                            .language(playgroundPosition.getPositionLanguageList().stream()
                                    .map(positionLanguage -> {
                                        return positionLanguage.getLanguageName().name();
                                    }).collect(Collectors.toList()))
                            .build();
                }).collect(Collectors.toList());
    }
}
