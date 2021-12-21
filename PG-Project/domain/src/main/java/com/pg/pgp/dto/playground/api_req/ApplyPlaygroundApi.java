package com.pg.pgp.dto.playground.api_req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotNull;

/**
 * User가 Playground에 참여신청 Api를 요청할 때 사용할 DTO
 */
@Getter
public class ApplyPlaygroundApi {
    @NotNull
    @JsonProperty("position_id")
    Long positionId;
}
