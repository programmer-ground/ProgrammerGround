package com.pg.programmerground.dto.playground.api_req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.*;
import java.util.List;

/**
 * playground 생성 dto
 * 제목
 * 설명
 * 인원
 * 찾는 포지션 정보
 */
@Getter
public class PlaygroundApi {
    @NotNull (message = "제목이 비었습니다")
    @Size(min = 1, max = 20, message = "최대 20글자까지 가능")
    private String title;

    @NotNull(message = "설명이 비었습니다")
    @NotBlank
    private String description;

    @NotNull
    @JsonProperty("leader_position")
    private String leaderPosition;

    @NotNull
    @Min(value = 1, message = "최소 1명")
    @Max(value = 10, message = "최대 10명")
    @JsonProperty("max_user_num")
    private Integer maxUserNum;

    @NotNull
    @JsonProperty("position")
    List<PositionApi> positionInfo;
}
