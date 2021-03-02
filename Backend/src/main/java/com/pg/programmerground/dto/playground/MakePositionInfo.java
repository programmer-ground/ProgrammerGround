package com.pg.programmerground.dto.playground;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class MakePositionInfo {
    @JsonProperty("position_name")
    private String positionName;

    @JsonProperty("position_max_num")
    private String positionMaxNum;
}
