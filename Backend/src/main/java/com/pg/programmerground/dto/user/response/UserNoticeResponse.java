package com.pg.programmerground.dto.user.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.pg.programmerground.domain.PlaygroundApply;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Builder
public class UserNoticeResponse {
    private final String title;
    private final String position;
    @JsonProperty(value = "user_name")
    private final String userName;

    public static List<UserNoticeResponse> ofList(List<PlaygroundApply> playgroundApplyList) {
        return playgroundApplyList.stream()
                .map(playgroundApply -> UserNoticeResponse.builder()
                        .title(playgroundApply.getPlayground().getTitle())
                        .userName(playgroundApply.getUser().getUserName())
                        .position(playgroundApply.getPlaygroundPosition().getPosition().name())
                        .build())
                .collect(Collectors.toList());
    }
}
