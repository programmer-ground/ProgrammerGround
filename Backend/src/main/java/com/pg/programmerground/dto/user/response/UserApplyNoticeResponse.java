package com.pg.programmerground.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.programmerground.domain.PlaygroundApply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Builder
@Getter
public class UserApplyNoticeResponse {
    @JsonProperty(value = "playground_title")
    private final String playgroundTitle;
    private final String position;

    public static List<UserApplyNoticeResponse> ofList(List<PlaygroundApply> playgroundApplyList) {
        return playgroundApplyList.stream()
                .map(playgroundApply -> UserApplyNoticeResponse.builder()
                        .playgroundTitle(playgroundApply.getPlayground().getTitle())
                        .position(playgroundApply.getPlaygroundPosition().getPosition().name()).build())
                .collect(Collectors.toList());
    }
}
