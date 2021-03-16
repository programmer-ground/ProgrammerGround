package com.pg.programmerground.dto.playground.api_req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * User가 Playground에 참여신청 Api를 요청할 때 사용할 DTO
 */
@Getter
public class ApplyPlaygroundApi {
    @JsonProperty("position_id")
    Long positionId;
}
