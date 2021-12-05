package com.pg.pgp.dto.playground.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class PlaygroundCardListResponse {
    @JsonProperty("playground_card")
    private final List<PlaygroundCardResponse> playgroundCardResponses;
}
