package com.pg.programmerground.dto.playground;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * 각 포지션별 요구되는 프로그래밍 언어
 */
@Getter
public class MakePositionLanguage {
    @JsonProperty("language_name")
    private String languageName;
}
