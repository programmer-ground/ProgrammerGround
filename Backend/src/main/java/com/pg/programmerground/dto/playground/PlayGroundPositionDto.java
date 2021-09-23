package com.pg.programmerground.dto.playground;

import com.pg.programmerground.domain.enumerated.Position;
import com.pg.programmerground.domain.enumerated.PositionLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PlayGroundPositionDto {
    private Long id;
    private Position position;
    private int maxPositionNum;
    private int currentPositionNum;
    private PositionLevel positionLevel;
    private boolean fullPosition;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    @Builder
    public PlayGroundPositionDto(Long id,
                                 Position position,
                                 int maxPositionNum,
                                 int currentPositionNum,
                                 PositionLevel positionLevel,
                                 boolean fullPosition,
                                 LocalDateTime createdAt,
                                 LocalDateTime modifiedAt) {
        this.id = id;
        this.position = position;
        this.maxPositionNum = maxPositionNum;
        this.currentPositionNum = currentPositionNum;
        this.positionLevel = positionLevel;
        this.fullPosition = fullPosition;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    };
}
