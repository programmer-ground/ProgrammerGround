package com.pg.programmerground.vo;

import lombok.Getter;

@Getter
public class PositionVo {
    private final int id;
    private final String content;

    public static PositionVo of(int id, String content) {
        return new PositionVo(id, content);
    }

    private PositionVo(int id, String content) {
        this.id = id;
        this.content = content;
    }
}
