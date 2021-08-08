package com.pg.programmerground.dto.playground.api_req;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * 각 포지션별 요구되는 프로그래밍 언어
 */
@Getter
public class PositionLanguageApi {
    @JsonProperty("language_name")
    @NotNull
    @NotBlank
    private String languageName;
}
