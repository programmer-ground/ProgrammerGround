package com.pg.pgp.dto.playground;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.pgp.domain.PlaygroundPosition;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PlaygroundPositionsDto {
    @JsonProperty("playground_positions")
    private final List<PlayGroundPositionDto> playgroundPositions;

    public PlaygroundPositionsDto(List<PlaygroundPosition> playgroundPositions) {
        this.playgroundPositions = playgroundPositions.stream()
                .map(element ->
                        PlayGroundPositionDto.builder()
                                .id(element.getId())
                                .position(element.getPosition())
                                .maxPositionNum(element.getMaxPositionNum())
                                .currentPositionNum(element.getCurrentPositionNum())
                                .positionLevel(element.getPositionLevel())
                                .fullPosition(element.isFullPosition())
                                .createdAt(element.getCreatedAt())
                                .modifiedAt(element.getModifiedAt())
                                .build()
                ).collect(Collectors.toList());
    }
}
