package com.pg.programmerground.dto.playground;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

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
    @JsonProperty("max_user_num")
    private Integer maxUserNum;
    List<MakePositionInfo> makePositionInfoList;
}
