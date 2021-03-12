package com.pg.programmerground.dto.playground;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

/**
 * 각 포지션 정보
 * 포지션 이름
 * 포지션 최대 인원
 */
@Getter
public class MakePositionInfoDto {
    @JsonProperty("position_name")
    private String positionName;

    @JsonProperty("position_max_num")
    private int positionMaxNum;

    @JsonProperty("position_level")
    private String positionLevel;

    private List<MakePositionLanguage> positionLanguage;
}
