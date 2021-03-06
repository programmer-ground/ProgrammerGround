package com.pg.programmerground.dto.playground;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * User가 Playground에 참여신청 Api를 요청할 때 사용할 DTO
 */
@Getter
public class ApplyPlaygroundDto {
    @JsonProperty("position_id")
    Long positionId;
}
