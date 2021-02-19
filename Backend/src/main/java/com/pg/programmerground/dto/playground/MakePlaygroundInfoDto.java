package com.pg.programmerground.dto.playground;

import lombok.Getter;

/**
 * playground 생성 dto
 * 제목
 * 설명
 * 인원
 *
 */
@Getter
public class MakePlaygroundInfoDto {
    private String title;
    private String description;
    private Integer maxUserNum;
}
