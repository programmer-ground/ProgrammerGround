package com.pg.pgp.domain.enumerated;

import com.pg.pgp.vo.PositionVo;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Getter
public enum Position {
    BACKEND(1, "BACKEND"),
    FRONTEND(2, "FRONTEND"),
    DESIGN(3, "DESIGN"),
    PLANNER(4, "PLANNER"),
    DEVOPS(5, "DEVOPS");

    private final int id;
    private final String content;

    Position(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public static List<PositionVo> toEntity() {
         return Arrays.stream(Position.class.getEnumConstants())
                .map(item -> PositionVo.of(item.getId(),
                        item.getContent()))
                .collect(Collectors.toList());
    }
}
