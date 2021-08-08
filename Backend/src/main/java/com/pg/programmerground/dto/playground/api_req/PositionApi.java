package com.pg.programmerground.dto.playground.api_req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 각 포지션 정보
 * 포지션 이름
 * 포지션 최대 인원
 */
@Getter
public class PositionApi {

    @NotNull
    @JsonProperty("position_name")
    private String positionName;

    @Min(value = 1, message = "최소 1명")
    @Max(value = 10, message = "최대 10명")
    @JsonProperty("position_max_num")
    private Integer positionMaxNum;

    @JsonProperty("position_level")
    @NotBlank
    @NotNull
    private String positionLevel;

    @JsonProperty("position_language")
    @NotNull
    private List<PositionLanguageApi> positionLanguage;
}
