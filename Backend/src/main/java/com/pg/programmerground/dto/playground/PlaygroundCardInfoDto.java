package com.pg.programmerground.dto.playground;

import lombok.AllArgsConstructor;
import lombok.Builder;

/**
 * 메인 페이지에 노출될 playground 리스트에 각각 표현될 정보들
 * 제목
 * 설명
 * 리더
 * 추후 추가...
 */
@AllArgsConstructor
@Builder
public class PlaygroundCardInfoDto {
    private final String title;
    private final String description;
    private final String leader;
}
